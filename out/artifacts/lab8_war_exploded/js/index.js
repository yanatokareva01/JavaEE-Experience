/* интерактивные часы */
dateContainer = document.getElementById('datetime');
setInterval(updateTime, 6000);
updateTime();

function updateTime() {
    curDateTime = new Date().toLocaleString();
    dateContainer.innerHTML = curDateTime;
}
