package com.notbad.hotfix.handler.op

import com.notbad.hotfix.handler.Op

class Minus : Op {
    override fun match(op: String): Boolean {
        return "-".equals(op)
    }

    override fun cal(a: Int, b: Int): Int {
        return a-b
    }
}