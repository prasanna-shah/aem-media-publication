$('#submit').on('click', function(){
    $('.box a').addClass('hide').removeClass('filtered');
    var enteredText = $('#search').val().toLowerCase();
    $('.box h2').each(function(){
        var filename = $(this).text().toLowerCase();
        if(filename.indexOf(enteredText) > -1){
            $(this).closest('a').removeClass('hide').addClass('filtered');
        }
    });
    paginate();
    $('#pagination a[data-val="1"]').click();
});
