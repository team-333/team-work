//document.write('<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">');
//document.write('<script src="https://code.jquery.com/jquery-1.12.4.js"></script>');
//document.write('<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>');

$(function() {
    $( ".RegDate" ).datepicker({
    	closeText: '닫기',
    	changeMonth: true,
    	changeYear: true,
    	nextText: '다음달',
    	prevText: '이전달',
    	dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
    	dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    	dateFormat: "yy-mm-dd",
    	showButtonPanel: true,
    	minDate: 0, // 선택할 수 있는 최소 날짜, (0 : 오늘 이전 날짜 선택 불가)
    	showAnim: "fadeIn", // show(기본), slideDown, fadeIn, slide
  		
    });
    $('.RegDate').datepicker('setDate', 'today');
    $('.ui-datepicker').addClass('notranslate');	// Nan/Nan/Nan 오류 수정코드
});