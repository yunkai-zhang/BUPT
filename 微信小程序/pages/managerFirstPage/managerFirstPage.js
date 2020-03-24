// pages/managerFirstPage/managerFirstPage.js
//获取应用实例
var tcity = require("../../utils/citys.js");

var app = getApp()//用于拿到app实例//我们提供了全局的getApp()函数，可以获取到小程序实例，一般用在各个子页面之中获取顶层应用。
//使用getApp的注意事项
// App()必须在 app.js 里调用，且不能调用多次。
// 不要在定义于 App() 内定义的函数中调用 getApp() ，使用 this 就可以拿到 app 实例。
// 不要在 onLaunch 里调用getCurrentPages() ，这个时候 page 还没有生成。
// 通过 getApp() 获取实例之后，不要私自调用生命周期函数。
Page({
  data: {
    provinces: [],//所有省份的数组
    province: "",//当前省份（显示的省份）
    citys: [],//当前省份所有城市的数组
    city: "",//当前城市
    countys: [],//当前省市的所有县的数组
    county: '',//当前县
    // value: [0, 0, 0],//用于切换省市后，把市县调整成第一个为当前显示//也用于和html的绑定，指示三个列中的哪个会显示//旧---------
    // values: [0, 0, 0],//用于储存原始的初始的三个column的分别的数据//只用于后台//旧--------------------------------------
    value: [0, 0, 0,0],//新-----------------------------------------------------------------------------------------
    values: [0, 0, 0,0],//新------------------------------------------------------------------------------------------

    machine:"",//-----------------------------------------------------------------------新
    machines:[],//---------------------------------------------------------------------新----
    condition: false,//用于控制点击确定取消后三个column弹起与否
    usedBatteryNum5:7,
    usedBatteryNum7:6,
    usedBatteryNumButton: 5,
    newBatteryNum5:4,
    newBatteryNum7:2,
    newBatteryNumButton:1
  },
  bindChange: function (e) {
    //console.log(e);
    var val = e.detail.value//获取事件传递的三个pickerviewcolme的信息，作为最新信息。使用其中包含三个列谁显示的元素value
    var t = this.data.values;//从values获取原始数据的原始的三个colume的信息
    var machines=this.data.machines;//把data中的数据引入bindChange函数中
    //内部values和外部value初始是一样的，但是改变picker后两者不一样了。所以为了良好区分内外和区分前台绑定和后台处理，使用了两个变量
    var cityData = this.data.cityData;//使用citys。js的内容，其在之前被引入了
    var machineData=this.data.machineData;

    if (val[0] != t[0]) {//如果造成picker view触发的是因为省份变化，把新省份的city和county压入数组
      console.log('province no ');
      const citys = [];//定义两个全局变量，对应于data中的项
      const countys = [];

      for (let i = 0; i < cityData[val[0]].sub.length; i++) {//把新省份value0的所有city压入数组
        citys.push(cityData[val[0]].sub[i].name)
      }
      for (let i = 0; i < cityData[val[0]].sub[0].sub.length; i++) {//把新省份value0的第一个市sub0的所有县压入数组
        countys.push(cityData[val[0]].sub[0].sub[i].name)
      }

      this.setData({
        province: this.data.provinces[val[0]],//设置province为当前的val0所代表的province。
        city: cityData[val[0]].sub[0].name,//设置变换成新省份的省份的第一个市为当前市
        citys: citys,//citys数组，当前省份的所有city构成
        county: cityData[val[0]].sub[0].sub[0].name,//设置新省份的第一个市的第一个县为当前县
        countys: countys,//由当前省份的当前市的所有县构成
        machine:machines[0],//使得最上方横杠内容及时变化
        values: val,//values数组设置为从picker-view的bindchange事件所得到的事件，作为新一代的原始数据
        value: [val[0], 0, 0,0]//省份变化时，把市和县变成默认的第一个。//其绑定在html中，用于指定三个列的分别第几个去显示
      })

      return;//结束当前函数，不返回任何值
    }
    if (val[1] != t[1]) {//如果造成picker view触发的是因为市变化，把新的city的所有county压入数组
      console.log('city no');
      const countys = [];//定义一个全局变量，对应data中的数据

      for (let i = 0; i < cityData[val[0]].sub[val[1]].sub.length; i++) {//把当前省份和变化后的市的所有县全部压入数组
        countys.push(cityData[val[0]].sub[val[1]].sub[i].name)
      }

      this.setData({
        city: this.data.citys[val[1]],//把data中初始城市设置成滑动后的新城市。
        county: cityData[val[0]].sub[val[1]].sub[0].name,//把data中初始县设置成滑动后的新城市的第一个县。
        countys: countys,//把data中的初始县数组，设置成当前省市的县的数组
        machine: machines[0],//使得最上方横杠内容及时变化
        values: val,//把新一代三个column的数据作为初始数据
        value: [val[0], val[1], 0,0]//使得市变化后县自动调整为第一个//对应前端更新时县为默认第一个
      })
      return;
    }
    if (val[2] != t[2]) {//如果县变化引起pickerview事件
      console.log('county no');
      this.setData({
        county: this.data.countys[val[2]],//把data中的当前县设置成事件传过来的县
        machine: machines[0],//使得最上方横杠内容及时变化
        values: val,//把data中的初始数组设置成当前事件传进来的数组。
        value: [val[0], val[1], val[2], 0]          //不需要改变value去重新定义某列哪项显示。//增加机器后让机器归位
      })
      return;
    }
    if (val[3] != t[3]) {//如果机器变化引起pickerview事件
      console.log('machine no');
      this.setData({
        machine: this.data.machines[val[3]],//把data中的当前机器设置成事件传过来的机器
        values: val,//把data中的初始数组设置成当前事件传进来的数组。
                   //不需要改变value去重新定义某列哪项显示。
      })
      return;
    }


  },
  open: function () {
    console.log('使用了open函数'),
 //   console.log('之前condition1等于'+condition1),//疑问-为什么condition1总是提示未定义，但是data中有定义啊，只是onload中没定义。而且condition在onload中也没有定义，却没报错。
    this.setData({
      condition: !this.data.condition,//打开界面时把数据使用情况condition设置为true//执行确定或取消后会执行这个函数使得html中的column收起来
      //condition1: !this.data.condition1
    //  condition: true//写了这个后就没办法通过确定收起了
    })
   // console.log('之后condition1等于' + condition1)
  },
  onLoad: function () {
    wx.showToast({
      title: '正在载入',
      icon: 'loading',
      image: '',
      duration: 2000,
      mask: true,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
    console.log("onLoad");
    var that = this;//定义一个变量，它即将承载所有的省市县信息

    tcity.init(that);//把变量that传给init，让init把它内部的省市县信息全部传到manaFst。js中的that上。

    var cityData = that.data.cityData;//在当前文件新建一个cityDta，让他直接作为省市县信息的承载数组
    var machineData=that.data.machineData;//在当前新建一个machineData，让他作为县里面机器编号的载体//新-----------------------


    const provinces = [];//在当前onload函数中创建省市县实例
    const citys = [];
    const countys = [];
    const machines=[];

    for (let i = 0; i < cityData.length; i++) {//初始化页面的，先把省的信息全部注入provinces数组
      provinces.push(cityData[i].name);
    }
    console.log('省份完成');
    for (let i = 0; i < cityData[0].sub.length; i++) {//初始化页面的，把第一个省的所有市压入citys数组
      citys.push(cityData[0].sub[i].name)
    }
    console.log('city完成');
    for (let i = 0; i < cityData[0].sub[0].sub.length; i++) {//初始化页面的，把第一个省的第一个市的所有县压入数组
      countys.push(cityData[0].sub[0].sub[i].name)
    }
    for (let i = 0; i < machineData.length; i++) {//初始化页面的，把与省市县独立的机器编号压入数组
      machines.push(machineData[i])
    }


    that.setData({//设置初始化信息
      'provinces': provinces,
      'citys': citys,
      'countys': countys,
      'machines':machines,
      'province': cityData[0].name,
      'city': cityData[0].sub[0].name,
      'county': cityData[0].sub[0].sub[0].name,
      'machine':machines[0],

    })
    console.log('初始化完成');


  }
})
