// pages/customerFirstPage/customerFirstPage.js
Page({
  // onShow(){//没必要不允许换电池时兑换
  //   this.setData({
  //     isGiving:flase,
  //   })
  // },
  onLoad(){
    var that=this;
     that.setData({
     isChanging:false,
     isGiving:false,
    //  wantNum:1,
    })
  },

//先把输入框中的数据输入数据库
  wantNum: function (e) {
    this.setData({
      wantNum : e.detail.value,
    })
    console.log(e.detail.value);
  },

  onTapToExchange:function(event){
    console.log(" tap ");
    //设置按钮动画
    var that=this;
    that.setData({
      isChanging: true,
    })
    setTimeout(function () {
      that.setData({
        isChanging: false,
      })
    }, 200);

    //处理兑换
    //数目不超，积分得够
    console.log("that.data.totalNumber=");
    console.log(that.data.totalNumber);
    console.log("that.data.wantNum");
    console.log(that.data.wantNum);
    console.log("that.data.creditValue");
    console.log(that.data.creditValue);
    console.log("(that.data.wantNum)*(that.data.credit5)");
    console.log((that.data.wantNum)*(that.data.credit5));
    if ((that.data.totalNumber >= that.data.wantNum) && (that.data.creditValue >= (that.data.wantNum)*(that.data.credit5)))
    {
      console.log("进入if");
      that.setData({
        creditValue: ((that.data.creditValue) - (that.data.wantNum) * (that.data.credit5)),
        totalNumber: 10,
        credit5: 3,
        count: "",//不专门对data中的count来setdata的话，页面中的count是永远不会显示的
      })
    }
    

  },
  
  giveButton:function(event){
    var that=this;//把page作为参数付给that
    that.setData({
      isGiving: true,
    })
    setTimeout(function () {//匿名函数里的this不是原来（匿名函数里外）的this了，必须在之前用that保存值
      that.setData({
        isGiving: false,
      })  
    }, 30000);//三十秒后投电池结束，同时按钮变色

    if(!that.data.counting){//如果没有正在计时
      //开始倒计时30秒
      countDown(that, 30);//调用一个外部的函数
    }
    else if (that.data.counting) {
      wx.showToast({
        title: '请勿重复点击',
        duration:500,
        icon:"loading"
      })
    
    }
  },
  
  data: {
  creditValue:30,
  totalNumber: 10,
  credit5:3,
  count:"",//不专门对data中的count来setdata的话，页面中的count是永远不会显示的
  remindTxt:"             ",
  counting:false,
  },

  /**
   * 生命周期函数--监听页面加载
  //  */
  // onLoad: function (options) {
  
  // },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})
//现在是page外
//倒计时30秒
function countDown(that, count) {//传入了page和倒计时总时
  if (count == 0) {
    that.setData({
      remindTxt: "",
      counting: false
    })
    wx.showToast({
      title: '装载电池结束',
      duration:1000,
    icon: ""
    })
    return;
  }
  that.setData({
    counting: true,
    remindTxt: "请在" + count + "秒内投入电池",
  })
  setTimeout(function () {//这个函数是关键，意味着1000ms执行一次小括号中的函数
    count--;
    countDown(that, count);
  }, 1000);
}