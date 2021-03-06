import Vue from 'vue'
Vue.filter("formatDate", function formatDate(value) {
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "年" + month + "月" + day + "日";
});
Vue.filter("formatDateTime", function formatDateTime(value) {
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "年" + month + "月" + day + "日" +" "+ hours + ":" + minutes;
});

Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1,                   //月份
    "d+": this.getDate(),                        //日
    "h+": this.getHours(),                       //小时
    "m+": this.getMinutes(),                     //分
    "s+": this.getSeconds(),                     //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds()                  //毫秒
  };

  //  获取年份 
  // ①
  if (/(y+)/i.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }

  for (var k in o) {
    // ②
    if (new RegExp("(" + k + ")", "i").test(fmt)) {
      fmt = fmt.replace(
        RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
  }
  return fmt;
}

