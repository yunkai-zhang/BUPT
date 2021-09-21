//app.js
App({
  // onLaunch: function () {
  //   // 展示本地存储能力
  //   var logs = wx.getStorageSync('logs') || []
  //   logs.unshift(Date.now())
  //   wx.setStorageSync('logs', logs)

  //   // 登录
  //   wx.login({
  //     success: res => {
  //       // 发送 res.code 到后台换取 openId, sessionKey, unionId
  //     }
  //   })
  //   // 获取用户信息
  //   wx.getSetting({
  //     success: res => {
  //       if (res.authSetting['scope.userInfo']) {
  //         // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
  //         wx.getUserInfo({
  //           success: res => {
  //             // 可以将 res 发送给后台解码出 unionId
  //             this.globalData.userInfo = res.userInfo

  //             // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
  //             // 所以此处加入 callback 以防止这种情况
  //             if (this.userInfoReadyCallback) {
  //               this.userInfoReadyCallback(res)
  //             }
  //           }
  //         })
  //       }
  //     }
  //   })
  // },
  // globalData: {
  //   userInfo: null
  // }

//该函数常在onload处调用，判断有没有个人信息
  // getUserInfo: function (cb) {//函数获取外界提供的当时页面的userInfo
  //   var that = this
  //   if (this.globalData.userInfo) {//如果调用该函数的页面的userinfo不为null
  //     typeof cb == "function" && cb(this.globalData.userInfo)//cb是一个函数，而且把userinfo付给cb函数
  //   } else {//如果userinfo为null
  //     //调用登录接口请求服务器去获得userinfo
  //     wx.login({
  //       success: function (res) {//成功得到code时
  //         wx.getUserInfo({//这个函数取得非私密内容
  //           success: function (res) {
  //             console.log(res);
  //             that.globalData.userInfo = res.userInfo//保存获取到的非隐私数据
  //             typeof cb == "function" && cb(that.globalData.userInfo)
  //           }
  //         })

  //         if (res.code) {//这个res是wx。login的res
  //           console.log(res);
  //           //发起网络请求自己的服务器，用xampp模拟
  //           wx.request({
  //             url: common.baseUrl + 'index.php/api/user/wxlogin',//这块放服务器的地址，一定要替换！！
  //             data: {
  //               code: res.code
  //             },
  //             success: function (res) {//请求成功时
  //               that.globalData.openid = res.data.openid//把openid保存到本地，但这是不恰当的。
  //               wx.setStorage({//把openid放入缓存
  //                 key: "openid",
  //                 data: res.data.openid
  //               })
  //             }
  //           })
  //         } else {
  //           console.log('获取用户登录态失败！' + res.errMsg)
  //         }
  //       }
  //     })
  //   }
  // },
  // globalData: {
  //   userInfo: null,
  //   openid: null
  // }
})