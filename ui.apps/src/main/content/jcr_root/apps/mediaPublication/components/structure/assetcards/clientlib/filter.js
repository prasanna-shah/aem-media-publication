function filter() {
    document.getElementById("filter-asset").classList.toggle("show");
}
$('#filter-asset a').on('click', function(){
    $('.box a').addClass('hide').removeClass('filtered');
    var asset = $(this).text();
    if(asset=='Videos'){
        $('.box .asset-video').removeClass('hide').addClass('filtered');
    }
    else if(asset=='Images'){
        $('.box .asset-image').removeClass('hide').addClass('filtered');
    }
    else if(asset=='PDF'){
        $('.box .asset-pdf').removeClass('hide').addClass('filtered');
    }
    else{
        $('.box a').removeClass('hide').addClass('filtered');
    }
    paginate();
    $('#pagination a[data-val="1"]').click();
});