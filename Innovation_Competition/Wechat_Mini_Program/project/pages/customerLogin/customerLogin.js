// pages/customerLogin/customerLogin.js
Page({
  onLoad(){//初始化数据，使按钮为无色
    var that=this;
    that.setData({
      logNotClicked:true,
    })
  },
  onShow() {//使得返回后按钮不为蓝色
    var that = this;
    that.setData({
      logNotClicked: true,
    })
  },
  // InfoRequestButton:function(event){
   
  // },
  // showToastErr() {
  //   　　const_this = this;
  //   　　_this.$wuxToast.show({
  //     　　type: 'forbidden',
  //     　　timer: 1500,
  //     　　color: '#fff',
  //     　　text: '禁止操作',
  //     　　success: () => console.log('禁止操作')
  //   　　})
  // 　　},
  bindsubmit:function(e){
    var that=this;//由于不同对象的this是不一样的，想针对谁的对象操作就要把那个对象先提出来保存，之后访问that//见44行
    console.log("aaaa");
    that.setData({
      logNotClicked: false
    })
      console.log(e.detail.value.machineIdInput)
      if (e.detail.value.machineIdInput != "")//这个地方设置对于机器编码的规范
      {//这里处理对于规范代码的操作
        that.setData({//由于不同对象的this是不一样的，想针对谁的对象操作就要把那个对象先提出来保存，之后访问that//此处的对象已经变成了匿名函数
          machineIdInput: e.detail.value.machineIdInput,
        }),
          wx.navigateTo({
            url: '../customerFirstPage/customerFirstPage',
          })
      }
      else if (e.detail.value.machineIdInput = " ")//处理不规范机器编码//引号中必须为空格表示没有输入
      {
        setTimeout(function () {//先让按钮显示按了，并且show提示，直到一秒后把匿名函数中的内容加入进程，使按钮褪色
          that.setData({//由于不同对象的this是不一样的，想针对谁的对象操作就要把那个对象先提出来保存，之后访问that
            logNotClicked: true,
          })
        }, 1000);

        wx.showToast({
          title: '请输入机器号码',
          icon: 'loading',
          duration: 1000
        })
      }
    

    console.log("bbbb");
    
  }
})