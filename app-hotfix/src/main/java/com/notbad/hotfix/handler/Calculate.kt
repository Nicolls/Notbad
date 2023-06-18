package com.notbad.hotfix.handler

import com.notbad.hotfix.handler.op.Minus
import com.notbad.hotfix.handler.op.Plus
import com.notbad.lib.common.LogUtils

object Calculate {
    private const val TAG = "Calculate"
    private val ops = arrayOf(Plus(), Minus())
    // 新增乘法支持 , 打开这个就可以测试热修复有没有成功了
//    private val ops = arrayOf(Plus(), Minus(), Mul())
//    class Mul:Op {
//        override fun match(op: String): Boolean {
//            return "x".equals(op)
//        }
//
//        override fun cal(a: Int, b: Int): Int {
//            return a*b
//        }
//    }


    @JvmStatic
    fun cal(a: Int, b: Int, op: String): Int {
        ops.forEach {
            if (it.match(op)) {
                return@cal it.cal(a, b)
            }
        }
        LogUtils.w(TAG, "un support op:$op")
        return 0
    }
}