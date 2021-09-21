var app = getApp()
Page({
  onLoad: function (options) {//load的l不大写会导致错误，而且不会提示函数错误
    var that=this;
    that.setData({//设置初始化信息
      // 'cusBgColor': rgb(247, 245, 245), 
      cusNotClick:true,   //1按，0不按 
      manaNotClick:true,
      
    })
  },

  cusLogButton:function(event){
    var that = this;
    that.setData({
    //'cusBgColor' : rgb(8,122,252),
      cusNotClick:false   //flase按，true不按
    })
    wx.login({
      success: function (res) {
        console.log(res.code)        //发送请求       
         wx.request({          
           url: 'http://localhost:8083/br.php', //接口地址         
            data: {code:res.code},         
             header: {           
                'content-type': 'application/json' //默认值          
             },          
             success: function (res) {            
               console.log(res.data)          
               }        
               })      
               }    
               })
        
      
    setTimeout(function () {
      // 新---------------------------------------------------------
      wx.redirectTo({
        url: '../customerLogin/customerLogin',
      })
    //新--------------------------------------------------------------
    }, 100);
  },

  managerLoginButton :function(event){
    var that = this;
    that.setData({
      //'cusBgColor' : rgb(8,122,252),
      manaNotClick: false   //flase按，true不按
    })
    setTimeout(function () {
      wx.redirectTo({
        url: '../managerLogin/managerLogin',
      })
    }, 100);
  },
  // data:{
  //   userInfo:null,
  // }
});
