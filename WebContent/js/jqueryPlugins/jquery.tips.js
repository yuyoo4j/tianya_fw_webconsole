/**
 * @fileOverview 工具提示,气泡提示 的插件
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 * @author keshin
 * @version 1.0
 * @date 2009-1-19
 */
(function() {
  /**
   * @name jQuery
   * @namespace
   * @class jQuery Library (http://jquery.com/)
   */
  
  /**
   * @name fn
   * @namespace
   * @class jQuery Library (http://jquery.com/)
   * @memberOf jQuery
   */

  
  /**
   * @description 给指定的元素设置多格式提示
   * @author keshin <br>
   * <b>备注：</b><br>
   * 元素的title属性总会被作为提示的主内容. 如果你在title中输入 :: ，则 :: 前的内容将被作为提示的标题。
   * title属性值允许使用html格式
   * @example 
   * // 示例：
   * $("a").tips({offsets:{x:10, y:20}}); // 给所有的链接设置提示
   * @param {JSON} options 配置项.可选，默认值如下：
   * <pre class="code">
   * {
    maxTitleChars: 30, // 提示的标题所能显示的最长字数。默认为 30. 
    showDelay: 100,    // 提示显示方法延迟执行的毫秒数 (默认为100 ms)
    hideDelay: 100,    // 提示消失方法延迟执行的毫秒数 (默认为100 ms)
    timeout:  0,       // 提示在显示多长时间后自动小时，0则不会自动小时  
    className: 'tool', //提示所用的样式名的前缀。默认为 ‘tool’.  
                            // 整个提示的样式为: tool-tip 
                            // 提示标题的样式为: tool-title 
                            // 文本内容的样式为: tool-text 
    offsets: {'x': 16, 'y': 16}, // 提示与鼠标的距离。一个包含x/y 属性的对象。
    tipType: 2    // 提示显示的方式，0为显示在元素根据offsets偏移的位置，固定
                            // 1为显示在事件触发时的偏移位置，固定
                            // 2为提示会随鼠标移动
   * }
   * </pre>
   * @return {jQuery} 可供链式操作
   * @memberOf jQuery.fn
   */
  jQuery.fn.tips = function(options) {
    var tipBox; // 显示tip时的容器  
    var timer;  // 延时器对象
    var currentTipWidth, currentTipHeight; // 当前tip的宽高
    
    // 初始化默认数据
    var opts = jQuery.extend({
      maxTitleChars: 30,
      showDelay: 100,
      hideDelay: 100,
      timeout:  0,
      className: 'tool',
      offsets: {'x': 16, 'y': 16},
      tipType: 2
    }, options || {});
    
    // 初始化tipBox
    if (!tipBox) {
      tipBox = jQuery("<div/>").addClass(opts.className + "-tip").css({
        'position': 'absolute',
        'top': '0',
        'left': '0',
        'display': 'none'
      }).html("<div/>").appendTo(jQuery("body"));
    }
    
    // 设置值
    this.each(function () {
      // 设置Tip标题和内容
      this.tipData = {}; // Tip数据，tipData.title为标题，tipData.text为正文内容
      if (this.tagName.toLowerCase() == "a" && this.href) {
        this.tipData.title = this.href.replace(/http:\/\/|https:\/\//, "");
      };
      
      if (this.title) {
        var dual = this.title.split("::");
        if (dual.length > 1) {
          this.tipData.title = jQuery.trim(dual[0]);
          this.tipData.text = jQuery.trim(dual[1]);
        } else {
          this.tipData.text = this.title;
        }
        jQuery(this).removeAttr("title");
      }
      
      // 设置截断
      // FIXME 如果是html代码，可能会出现截断不正常
      if (this.tipData.title 
          && this.tipData.title.length > opts.maxTitleChars) {
        this.tipData.title = this.tipData.title.substr(0, opts.maxTitleChars - 1) + "&hellip;";
      }
      
      // 添加监听器
      jQuery(this).mouseover(function(evt) {
        start(this);
        
        if (opts.tipType == 0) {
          position(this);
        } else {
          locate(evt);
        }
      });
      
      if (opts.tipType == 2) {
        jQuery(this).mousemove(locate);
      }
      
      jQuery(this).mouseout(end).mousedown(end);
    }); // end each
  
    function start(el) {
      var wrapper = tipBox.children().empty();
      
      // do tip
      var tipData = el.tipData;
      
      // do title
      if (tipData.title) {
        jQuery("<span/>").html(tipData.title)
          .wrap("<div class='" + opts.className + "-title'></div>")
          .parent().appendTo(wrapper);
      }
      
      // do text
      if (tipData.text) {
        jQuery("<span/>").html(tipData.text)
          .wrap("<div class='" + opts.className + "-text'></div>")
          .parent().appendTo(wrapper);
      }
      
      // 获取当前Tipbox的高宽
      currentTipWidth = tipBox.width();
      currentTipHeight = tipBox.height();
      
      window.clearTimeout(timer);
      
      timer = window.setTimeout(show, opts.showDelay);
    }
    
    function end() {
      window.clearTimeout(timer);
      timer = window.setTimeout(hide, opts.hideDelay);
    }
    
    function position(el) {
      var offset = jQuery(el).offset();
      tipBox.css({
        left: offset.left + opts.offsets.x,
        top: offset.top + opts.offsets.y
      });
    }
    
    function locate(evt) {
      var left, top;
      var pageScrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
      var pageScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      left = evt.pageX + opts.offsets.x;
      if (left + currentTipWidth - pageScrollLeft > jQuery(window).width()) {
        left = evt.pageX - opts.offsets.x - tipBox.width();
      }
      top = evt.pageY + opts.offsets.y;
      if (top + currentTipHeight - pageScrollTop > jQuery(window).height()) {
        top = evt.pageY - opts.offsets.y - tipBox.heigth();
      }
      tipBox.css({left:left, top:top});
    }
    
    function show() {
      opts.timeout && (timer = window.setTimeout(hide, opts.timeout));
      tipBox.show();
    }
    
    function hide() {
      tipBox.hide();
    }
    
    return this;
  }; // end plugin
})(jQuery);
