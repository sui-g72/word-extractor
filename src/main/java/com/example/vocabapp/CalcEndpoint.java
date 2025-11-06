package com.example.vocabapp;

    class Result {
        private int left;
        private int right;
        private long answer;

        public Result(int left, int right, long answer) {
            this.left = left;
            this.right = right;
            this.answer = answer;
        }

        public Result() {
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public long getAnswer() {
            return answer;
        }

        public void setAnswer(long answer) {
            this.answer = answer;
        }
    }
