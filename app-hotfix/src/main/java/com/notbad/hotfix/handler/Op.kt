package com.notbad.hotfix.handler

interface Op {
    fun match(op:String):Boolean
    fun cal(a:Int,b:Int):Int
}