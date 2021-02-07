package com.sthoksdevs.sthoks.activities;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.database.MyDb;
import com.sthoksdevs.sthoks.fragments.CountDownFragment;
import com.sthoksdevs.sthoks.fragments.RdoGroupFragment;
import com.sthoksdevs.sthoks.interfaces.RdoGroupInterface;
import com.sthoksdevs.sthoks.interfaces.TimerInterface;
import java.util.Objects;
import java.util.Random;

public class QuizActivity extends FragmentActivity implements TimerInterface, RdoGroupInterface {
    AlertDialog alert;
    public String answer;
    Button answerBtn;
    String answerResult = "No result";
    TextView countDisplay;
    String displayedTime;
    boolean isDbUpdated = false;
    boolean isDialogShown = false;
    boolean isTimeUp = false;
    private Cursor mCursor;
    private CountDownFragment mFragment;
    private RdoGroupFragment mRdoFragment;
    private MyDb myDb;
    ProgressBar progressBar;
    public String question;
    TextView questionDisplay;
    public int questionPos;
    String[] questionsArray;
    public int seconds;
    String skill;
    TextView skillTitle;
    long timeCount;

    /* access modifiers changed from: protected */
    @Override // android.support.p000v4.app.SupportActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_quiz);
        this.skillTitle = (TextView) findViewById(R.id.skillTitle);
        this.countDisplay = (TextView) findViewById(R.id.countDisplay);
        this.questionDisplay = (TextView) findViewById(R.id.questionTv);
        this.answerBtn = (Button) findViewById(R.id.answerBtn);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.myDb = new MyDb(this);
        if (bundle == null) {
            this.skill = getIntent().getStringExtra("skill");
            if (this.skill.equals("html") || this.skill.equals("css") || this.skill.equals("csharp")) {
                if (this.skill.equals("html")) {
                    this.skillTitle.setText("HTML 5");
                }
                if (this.skill.equals("css")) {
                    this.skillTitle.setText("CSS 3");
                }
                if (this.skill.equals("csharp")) {
                    this.skillTitle.setText("C#");
                }
            } else {
                this.skillTitle.setText(this.skill.toUpperCase());
            }
            randomiseQuestions();
            this.answer = "No Answer";
            this.timeCount = 11000;
        }
        this.mRdoFragment = new RdoGroupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.rdoFragment, new RdoGroupFragment()).commit();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p000v4.app.SupportActivity, android.support.p000v4.app.FragmentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds", this.seconds);
        bundle.putLong("count", this.timeCount);
        bundle.putString("skill", this.skill);
        bundle.putString("answer", this.answer);
        bundle.putBoolean("isTimeUp", this.isTimeUp);
        bundle.putBoolean("isDialogShown", this.isDialogShown);
        bundle.putString("displayTxt", this.countDisplay.getText().toString());
        bundle.putString("question", this.questionDisplay.getText().toString());
        bundle.putString("skillTitleTxt", this.skillTitle.getText().toString());
        bundle.putString("answerResult", this.answerResult);
        bundle.putBoolean("dbUpdated", this.isDbUpdated);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.skill = bundle.getString("skill");
            this.seconds = bundle.getInt("seconds");
            this.timeCount = bundle.getLong("count");
            this.answer = bundle.getString("answer");
            this.isTimeUp = bundle.getBoolean("isTimeUp");
            this.isDialogShown = bundle.getBoolean("isDialogShown");
            this.countDisplay.setText(bundle.getString("displayTxt"));
            this.questionDisplay.setText(bundle.getString("question"));
            this.skillTitle.setText(bundle.getString("skillTitleTxt"));
            this.answerResult = bundle.getString("answerResult");
            this.isDbUpdated = bundle.getBoolean("dbUpdated");
        }
        super.onRestoreInstanceState(bundle);
    }

    private static void keepDialog(AlertDialog alertDialog) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(((Window) Objects.requireNonNull(alertDialog.getWindow())).getAttributes());
        layoutParams.width = -2;
        layoutParams.height = -2;
        alertDialog.getWindow().setAttributes(layoutParams);
    }

    private void randomiseQuestions() {
        Random random = new Random();
        this.questionsArray = getResources().getStringArray(getResources().getIdentifier(this.skill + "_questions", "array", getPackageName()));
        this.questionPos = random.nextInt(this.questionsArray.length);
        this.question = this.questionsArray[this.questionPos];
        this.questionDisplay.setText(this.question);
    }

    @Override // android.support.p000v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (this.mFragment == null) {
            this.mFragment = CountDownFragment.getInstance(getSupportFragmentManager(), this.timeCount);
            if (!this.isDialogShown) {
                this.mFragment.startTimer(this.timeCount);
            }
        } else if (!this.isDialogShown) {
            this.mFragment.startTimer(this.timeCount);
        }
        this.answerBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.sthoksdevs.sthoks.activities.QuizActivity.View$OnClickListenerC06391 */

            public void onClick(View view) {
                if (QuizActivity.this.mFragment != null) {
                    QuizActivity.this.mFragment.cancelTimer();
                }
                if (QuizActivity.this.progressBar.getVisibility() == View.GONE) {
                    QuizActivity.this.progressBar.setVisibility(View.VISIBLE);
                }
                QuizActivity.this.checkAnswer();
            }
        });
    }

    @Override // android.support.p000v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        if (this.mFragment != null) {
            this.mFragment.cancelTimer();
        }
    }

    @Override // android.support.p000v4.app.FragmentActivity
    public void onStop() {
        super.onStop();
        if (this.mFragment != null) {
            this.mFragment.cancelTimer();
        }
    }

    @Override // android.support.p000v4.app.FragmentActivity
    public void onBackPressed() {
        if (!this.isTimeUp) {
            this.mFragment.cancelTimer();
            if (this.questionDisplay.getVisibility() == View.VISIBLE) {
                this.questionDisplay.setVisibility(View.INVISIBLE);
            }
            this.alert = new AlertDialog.Builder(this).setCancelable(false).setTitle("Quit quiz").setMessage("Are you sure you want to quit the quiz?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnClickListenerC06453 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    QuizActivity.this.finish();
                    QuizActivity.this.isDialogShown = false;
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnClickListenerC06442 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (QuizActivity.this.questionDisplay.getVisibility() == View.INVISIBLE) {
                        QuizActivity.this.questionDisplay.setVisibility(View.VISIBLE);
                    }
                    QuizActivity.this.isDialogShown = false;
                    if (QuizActivity.this.mFragment != null) {
                        QuizActivity.this.mFragment.startTimer(QuizActivity.this.timeCount);
                    }
                }
            }).create();
            this.alert.setOnShowListener(new DialogInterface.OnShowListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnShowListenerC06464 */

                public void onShow(DialogInterface dialogInterface) {
                    QuizActivity.this.alert.getButton(-2).setBackground(QuizActivity.this.getResources().getDrawable(R.drawable.whiteborder_blackbg_btn));
                    QuizActivity.this.alert.getButton(-1).setBackground(QuizActivity.this.getResources().getDrawable(R.drawable.whiteborder_blackbg_btn));
                }
            });
            this.alert.show();
            keepDialog(this.alert);
            this.isDialogShown = true;
            return;
        }
        if (this.mFragment != null) {
            this.mFragment.cancelTimer();
        }
        super.onBackPressed();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override // com.sthoksdevs.sthoks.interfaces.TimerInterface
    public void updateUI(String str) {
        this.seconds = Integer.valueOf(str).intValue();
        this.countDisplay.setText(str);
        this.timeCount = (long) (Integer.valueOf(str).intValue() * 1000);
        if (str.equals("0")) {
            this.isTimeUp = true;
            this.answerBtn.setEnabled(false);
            if (this.answer.equals("No Answer")) {
                noAnswer();
            } else {
                checkAnswer();
            }
        } else {
            this.answerBtn.setEnabled(true);
        }
        this.displayedTime = this.countDisplay.getText().toString();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkAnswer() {
        Log.d("a1 = " + this.answer, " a2 = " + this.answerResult);
        if (this.answerResult.equals("No result")) {
            this.mCursor = this.myDb.checkAnswer(this.question);
            if (this.mCursor != null && this.mCursor.moveToFirst() && this.mCursor.getCount() > 0) {
                this.answerResult = this.mCursor.getString(0);
            }
        }
        if (this.answerResult.equals(this.answer)) {
            determineLevel();
        } else {
            final AlertDialog create = new AlertDialog.Builder(this).setCancelable(false).setTitle("Oops").setMessage("Incorrect answer.").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnClickListenerC06486 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    QuizActivity.this.isDialogShown = false;
                    QuizActivity.this.retry();
                }
            }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnClickListenerC06475 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    QuizActivity.this.finish();
                    QuizActivity.this.isDialogShown = false;
                }
            }).create();
            create.setOnShowListener(new DialogInterface.OnShowListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnShowListenerC06497 */

                public void onShow(DialogInterface dialogInterface) {
                    create.getButton(-2).setBackground(QuizActivity.this.getResources().getDrawable(R.drawable.whiteborder_blackbg_btn));
                    create.getButton(-1).setBackground(QuizActivity.this.getResources().getDrawable(R.drawable.whiteborder_blackbg_btn));
                }
            });
            create.show();
            keepDialog(create);
            this.isDialogShown = true;
        }
        if (!this.isDbUpdated) {
            this.myDb.addToQuiz(this.question, "Correct", String.valueOf(this.seconds));
        }
        if (this.progressBar.getVisibility() == View.VISIBLE) {
            this.progressBar.setVisibility(8);
        }
    }

    @Override // com.sthoksdevs.sthoks.interfaces.RdoGroupInterface
    public void updateAnswer(String str) {
        this.answer = str;
    }

    private void noAnswer() {
        this.answerBtn.setEnabled(false);
        final AlertDialog create = new AlertDialog.Builder(this).setCancelable(false).setTitle("Time Up").setMessage("You have not made a selection. Sorry, you did not win anything.").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnClickListenerC06519 */

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                QuizActivity.this.isDialogShown = false;
                QuizActivity.this.retry();
            }
        }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnClickListenerC06508 */

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                QuizActivity.this.finish();
                QuizActivity.this.isDialogShown = false;
            }
        }).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            /* class com.sthoksdevs.sthoks.activities.QuizActivity.DialogInterface$OnShowListenerC064010 */

            public void onShow(DialogInterface dialogInterface) {
                create.getButton(-2).setBackground(QuizActivity.this.getResources().getDrawable(R.drawable.whiteborder_blackbg_btn));
                create.getButton(-1).setBackground(QuizActivity.this.getResources().getDrawable(R.drawable.whiteborder_blackbg_btn));
            }
        });
        create.show();
        keepDialog(create);
        this.isDialogShown = true;
    }

    private void determineLevel() {
        if (this.seconds <= 4) {
            View inflate = getLayoutInflater().inflate(R.layout.layout_bronze_stars, (ViewGroup) null);
            final AlertDialog create = new AlertDialog.Builder(this).setCancelable(false).setView(inflate).create();
            ((Button) inflate.findViewById(R.id.closeBtn)).setOnClickListener(new View.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.View$OnClickListenerC064111 */

                public void onClick(View view) {
                    create.dismiss();
                    QuizActivity.this.isDialogShown = false;
                    QuizActivity.this.finish();
                }
            });
            create.show();
            keepDialog(create);
            this.isDialogShown = true;
        }
        if (this.seconds <= 7 && this.seconds >= 5) {
            View inflate2 = getLayoutInflater().inflate(R.layout.layout_silver_stars, (ViewGroup) null);
            final AlertDialog create2 = new AlertDialog.Builder(this).setCancelable(false).setView(inflate2).create();
            ((Button) inflate2.findViewById(R.id.closeBtn)).setOnClickListener(new View.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.View$OnClickListenerC064212 */

                public void onClick(View view) {
                    create2.dismiss();
                    QuizActivity.this.isDialogShown = false;
                    QuizActivity.this.finish();
                }
            });
            create2.show();
            keepDialog(create2);
            this.isDialogShown = true;
        }
        if (this.seconds >= 8) {
            View inflate3 = getLayoutInflater().inflate(R.layout.layout_gold_stars, (ViewGroup) null);
            final AlertDialog create3 = new AlertDialog.Builder(this).setCancelable(false).setView(inflate3).create();
            ((Button) inflate3.findViewById(R.id.closeBtn)).setOnClickListener(new View.OnClickListener() {
                /* class com.sthoksdevs.sthoks.activities.QuizActivity.View$OnClickListenerC064313 */

                public void onClick(View view) {
                    create3.dismiss();
                    QuizActivity.this.isDialogShown = false;
                    QuizActivity.this.finish();
                }
            });
            create3.show();
            keepDialog(create3);
            this.isDialogShown = true;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void retry() {
        randomiseQuestions();
        this.answerBtn.setEnabled(true);
        this.answer = "No Answer";
        this.answerResult = "No result";
        this.mRdoFragment = new RdoGroupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.rdoFragment, new RdoGroupFragment()).commit();
        this.timeCount = 11000;
        if (this.mFragment == null) {
            this.mFragment = CountDownFragment.getInstance(getSupportFragmentManager(), this.timeCount);
            if (!this.isDialogShown) {
                this.mFragment.startTimer(this.timeCount);
            }
        } else if (!this.isDialogShown) {
            this.mFragment.startTimer(this.timeCount);
        }
    }
}
