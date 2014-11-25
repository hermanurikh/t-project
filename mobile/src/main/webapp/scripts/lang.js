/**
 * Created by german on 25.11.14.
 */
function changeEng() {
    $.ajax({
        url: 'cp_language_en',
        type: 'GET',
        async:false,
        success: history.go(0) });
}
function changeRus() {
    $.ajax({
        url: 'cp_language_ru',
        type: 'GET',
        async:false,
        success: history.go(0) });
}