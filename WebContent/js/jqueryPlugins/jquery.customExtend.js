
jQuery.extend({
  toQueryString: function(o) {

	var type = typeof(o);
    if (type == "undefined" || null === o) {
       o = this;
    }

	var jsonParams = 'jsonParams=true'; 
	
    if (type == "number" || type == "boolean") {
        return jsonParams + "&" + o + "=" + o;
    }   

    // Is it a string?
    if (type == "string") {
    	var str = jQuery.quoteString(o);
        return jsonParams + "&" + str + "=" + str;
    }
    
    // Does it have a .toQueryString function?
    if (type == "object" && type != "function") {
    	for (var k in o) { 
    		var key = k + '';
    		var val = o[k] + '';
    		jsonParams += "&" + key + "=" + encodeURI(val);
    	}
    } else {
    	throw new TypeError("type error:" + type);
    }
      
    return jsonParams;
  },
  encodeBase64URLSafe: function(input) {
	  
	  var o = input.replace(/\+/g, '.');
	  return o.replace(/\//g, '_');
  },
  decodeBase64URLSafe: function(input) {
	  
	  var o = input.replace(/\./g, '+');
	  return o.replace(/\_/g, '/');
  }
}); 
