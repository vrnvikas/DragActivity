package com.materialtest.vikaskumar.dragactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimationTest extends AppCompatActivity {

    private static int[] towers = {R.id.tower_1, R.id.tower_2, R.id.tower_3};
    private static final int UNDECIDED = -1;
    private int fromTower = UNDECIDED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        TextView block1 = (TextView) findViewById(R.id.block_1);
        Animation drop = AnimationUtils.loadAnimation(this, R.animator.block_move_right);
        block1.startAnimation(drop);


    }


    private class BlockMover {
        private int to, from;
        View block;

        protected BlockMover(View block, int from, int to) {
            this.block = block;
            this.to = to;
            this.from = from;
        }

        public void move() {
            Animation removeAnimation = AnimationUtils.loadAnimation(
                    AnimationTest.this, R.animator.block_move_right);
            block.startAnimation(removeAnimation);
        }
    }


    private class TowerPicker implements View.OnClickListener {
        private int towerIndex;
        public TowerPicker (int towerIndex) {
            this.towerIndex = towerIndex;
        }
        public void onClick(View v) {
            if (fromTower == UNDECIDED) {
                ViewGroup tower =
                        (ViewGroup) findViewById(towers[towerIndex]);
                if (tower.getChildCount()>0) {
                    fromTower = towerIndex;
                }
            } else {
                ViewGroup fromTowerView =
                        (ViewGroup) findViewById(towers[fromTower]);
                if (fromTower != towerIndex) {
                    ViewGroup toTowerView =
                            (ViewGroup) findViewById(towers[towerIndex]);
                    View block = fromTowerView.getChildAt(0);
                    View supportingBlock = toTowerView.getChildAt(0);
                    if (supportingBlock == null
                            || supportingBlock.getWidth()>block.getWidth()) {
                        (new BlockMover (block,fromTower, towerIndex)).move();
                    }
                }
                fromTower = UNDECIDED;
            }
        }
    }

}
