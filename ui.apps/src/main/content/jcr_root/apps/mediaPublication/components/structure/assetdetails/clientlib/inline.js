$('#open-inline').on('click', function(){
	var asseturl = $('.asset-url').text();
    $('img').remove();
    $('iframe').remove();
    var inlineHtml = '<iframe width="750" height="450" src='+asseturl+'></iframe>';
    $('.imag').append(inlineHtml);
});