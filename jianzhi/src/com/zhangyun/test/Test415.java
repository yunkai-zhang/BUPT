package com.zhangyun.test;

public class Test415 {
    public String addStrings(String num1, String num2) {
        /**
         字符串的长度可以达到10000，说明最高位有1万位，超过了一般数据类型的范围；这里使用char数组手动相加。
         */
        //把两个字符串转化为字符数组
        char[] num1char=num1.toCharArray();
        char[] num2char=num2.toCharArray();

        //从个位开始往前加，就是从数组的末尾开始往前处理
        int i=1;//i表示两个数组当前处理的是倒数第几位
        //设置做加法时的进位
        int jinwei=0;
        //新建数组保存结果，长度是两个数组的长的长度那个加一；并记录结果数组的实际长度，防止预留位没被用上
        char[] res=new char[(num1char.length>num2char.length?num1char.length:num2char.length)+1];
        int resLength=0;
        while((num1char.length-i>=0)&&(num2char.length-i>=0)){//如果有一个数组被遍历完了就结束
            //取出两个数组的当前位相加
            int num1curInt=num1char[num1char.length-i]-'0';//!!!字符直接相减，可以得到整数表示的字符间的差值
            int num2curInt=num2char[num2char.length-i]-'0';
            int curAdd=num1curInt+num2curInt+jinwei;
            //相加后先清空进位
            jinwei=0;

            //处理相加的结果
            if(curAdd>9){//更新进位，和更新要被放入结果数组的值
                jinwei=1;
                curAdd-=10;
            }
            //字符可以通过ascii直接转化为数字；数字不能直接住哪胡为字符，会用到charat，参考http://t.csdn.cn/443FM
            String curAddStr=Integer.toString(curAdd);
            char curAddChar=curAddStr.charAt(0);
            res[res.length-i]=curAddChar;
            resLength++;
        }

        //处理到这里后，有一个列表处理完了，把剩下的那个处理完毕
        while(num1char.length-i>=0){
            //相加
            int num1curInt=num1char[num1char.length-i]-'0';
            int curAdd=num1curInt+jinwei;
            //相加后先清空进位
            jinwei=0;

            //保存结果
            if(curAdd>9){//更新进位，和更新要被放入结果数组的值
                jinwei=1;
                curAdd-=10;
            }
            String curAddStr=Integer.toString(curAdd);
            char curAddChar=curAddStr.charAt(0);
            res[res.length-i]=curAddChar;
            resLength++;
        }
        while(num2char.length-i>=0){
            //相加
            int num2curInt=num2char[num2char.length-i]-'0';
            int curAdd=num2curInt+jinwei;
            //相加后先清空进位
            jinwei=0;

            //保存结果
            if(curAdd>9){//更新进位，和更新要被放入结果数组的值
                jinwei=1;
                curAdd-=10;
            }
            String curAddStr=Integer.toString(curAdd);
            char curAddChar=curAddStr.charAt(0);
            res[res.length-i]=curAddChar;
            resLength++;
        }

        //如果两个列表都处理完了还有进位，就把进位放在res的第一个位置也就是唯一还空着的位置
        if(jinwei!=0){
            res[0]='1';
        }

        //把字符数组转化为字符串
        StringBuilder sb=new StringBuilder();
        for(char curCharOfRes:res){
            sb.append(curCharOfRes);
        }
        String resString=sb.toString();
        return jinwei==0?resString.substring(1,res.length):resString;//如果最后没有进位就截断第一位
    }

    public static void main(String[] args) {
        Test415 test415=new Test415();
        System.out.println("addStrings:"+test415.addStrings("11","123"));
    }
}
