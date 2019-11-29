var totalCards,paginationNum,totalPages;

function paginate() {
    totalCards = $('.box a.filtered').length;
    paginationNum = 8;
    totalPages = 1;
    totalPages = Math.ceil(totalCards/paginationNum);
    if(totalPages == 0){
		$('#pagination').html('<h2 class="no-asset">No Asset Found</h2>');
        return;
    }

    var pageinHtml = '<a href="#" data-val="-1">&laquo;</a>';
    for(var i=1; i<=totalPages; i++){
        pageinHtml += '<a href="#" data-val="'+i+'">'+i+'</a>';
    }
    pageinHtml += '<a href="#" data-val="99">&raquo;</a>';
    $('#pagination').html(pageinHtml);
    $('#pagination a[data-val="1"]').addClass('active');

    $('.pagination a').on('click', function(){
        var index = $(this).data('val');
        
        if(index==-1){
            index = $('#pagination a[class="active"]').data('val');
            if(index<=1){
                return;
            }
            $('#pagination a:nth-child('+index+')').click();
            return;
        }
        
        if(index==99){
            index = $('#pagination a[class="active"]').data('val') + 2;
            console.log(index);
            if(index>=totalPages+2){
                return;
            }
            $('#pagination a:nth-child('+index+')').click();
            return;
        }
        
        $('.pagination a').removeClass('active');
        $(this).addClass('active');
        var cards = $('.box a.filtered');
        cards.addClass('hide');
        var startCount = (index-1)*paginationNum + 1;
        for(var i=startCount; i<=(index*paginationNum); i++){
            $(cards[i-1]).removeClass('hide');
        }
    });
}
$('.box a').addClass('filtered');
paginate();

$('#pagination a[data-val="1"]').click();