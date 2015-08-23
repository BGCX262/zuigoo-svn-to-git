function SlideBox(container, frequency, direction,xishu) {
 if (typeof(container) == 'string') {
  container = document.getElementById(container);
 }
 this.container = container;
 this.frequency = frequency;
 this.direction = direction;
 this.xishu = xishu;
 this.films = [];
 var divs = this.container.getElementsByTagName('div');
 for (var i = 0; i < divs.length; i++) {
  if (divs[i].className == 'slideFilm') {
   divs[i].onmouseover = function(self)
    {
     return function()
      {
       self._mouseover()
      };
    }(this);
   divs[i].onmouseout = function(self){return function(){self._mouseout()};}(this);
   this.films[this.films.length] = divs[i];
  }
 }
 this._playTimeoutId = null;
 this._slideTimeoutId = null;
 this._slidable = true;
 var isIE5 = navigator.userAgent.toLowerCase().indexOf("msie 5")>0;
 if (!isIE5)
  this._loop();
}

SlideBox.prototype = {
 _loop : function() {
  var sb = this;
  this._playTimeoutId = setTimeout(function(){sb._slide()}, this.frequency);
 },

 _slide : function() {
  var sb = this;
  var _slide = function() {
   if (!sb._slidable) return;
   var c = sb.container;
   if (sb.direction == 'top') {
    if (c.scrollTop < c.offsetHeight/sb.xishu-2) {
     c.scrollTop += 2;
    } else {
     clearInterval(sb._slideTimeoutId);
     sb._loop();
     var ul = c.getElementsByTagName('ul')[0];
     ul.appendChild(c.getElementsByTagName('li')[0]);
     c.scrollTop = 0;
    }
   } else if (sb.direction == 'left') {
    if (c.scrollLeft < c.offsetWidth/sb.xishu-2) {
     c.scrollLeft += 2;
    } else {
     clearInterval(sb._slideTimeoutId);
     sb._loop();
     var ul = c.getElementsByTagName('ul')[0];
     ul.appendChild(c.getElementsByTagName('li')[0]);
     c.scrollLeft = 0;
    }
   }
  }

  this._slideTimeoutId = setInterval(_slide, 10);
 },

 _mouseover : function() {
  this._slidable = false;
 },

 _mouseout : function() {
  this._slidable = true;
 }
}