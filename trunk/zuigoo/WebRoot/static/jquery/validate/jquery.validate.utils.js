//email
function isVEmail(str)
{
	var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	return reg.test(str);
}
//double
function isVDouble(str)
{
	if(str=='')
		return false;
	var reg=/^([+-]?)\\d*\\.\\d+$/;
	return reg.test(str);
}
//integer
function isVInteger(str)
{
	if(str=='')
	return false;
	var reg=/^[0-9]*$/;
	return reg.test(str);
}
//url
function isVURL(str)
{
	//var reg=/^http[s]?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	var reg=/^[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	return reg.test(str);
}
//mobile
function isVMobile(str)
{
	var reg=/^(13|15)[0-9]{9}$/;
	return reg.test(str);
}
//qq
function isVQQ(str)
{
	var reg=/^[1-9][0-9]{4,11}$/;
	return reg.test(str);
}
//ip
function isVIP(str)
{
	var reg=/^\s*(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\s*(\.\s*(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\s*){3}$/;
	return reg.test(str);
}
//mac
function isVMAC(str)
{
	var reg=/^\s*[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}\s*$/;
	return reg.test(str);
}

//date eg: (2003-12-05)
function isVDate(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
	if(r==null)return false; 
	var d= new Date(r[1], r[3]-1, r[4]); 
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}

//date time，eg:(2003-12-05 13:04:06)
function isVDateTime(str)
{
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	var r = str.match(reg); 
	if(r==null) return false; 
	var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}
//radio  checkbox
function isChecked(id){
    var ReturnVal = false;
    $("#" + id).find('input[type="radio"]').each(function(){
        if ($(this).is(":checked")) 
            ReturnVal = true;
    });
    $("#" + id).find('input[type="checkbox"]').each(function(){
        if ($(this).is(":checked")) 
            ReturnVal = true;
    });
    return ReturnVal;
}
//radio  checkbox
function isVSelected(str){
    var flag = true;
    if(str=='-1')
	flag=false;
    return flag;
}