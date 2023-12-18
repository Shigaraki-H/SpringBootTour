let date = new Date();
let today = date.getDate();
let currentMonth = date.getMonth();
let currentYear = date.getFullYear();

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('calendar').innerHTML = createCalendar(currentMonth, currentYear);
    document.getElementById('prev').addEventListener('click', function() {
        date.setDate(today - 7);
        today = date.getDate();
        currentMonth = date.getMonth();
        currentYear = date.getFullYear();
        document.getElementById('calendar').innerHTML = createCalendar(currentMonth, currentYear);
    });
    document.getElementById('next').addEventListener('click', function() {
        date.setDate(today + 7);
        today = date.getDate();
        currentMonth = date.getMonth();
        currentYear = date.getFullYear();
        document.getElementById('calendar').innerHTML = createCalendar(currentMonth, currentYear);
    });
});

function createCalendar(month, year) {
    const monthDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    let calendarHTML = '<table class="calendar"><thead><tr>';

    for (let i = 0; i < 7; i++) {
        if (i === 0) {
            calendarHTML += `<th class="sun">${monthDays[i]}</th>`;
        } else if (i === 6) {
            calendarHTML += `<th class="sat">${monthDays[i]}</th>`;
        } else {
            calendarHTML += `<th>${monthDays[i]}</th>`;
        }
    }

    calendarHTML += '</tr></thead><tbody>';

    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const firstDay = new Date(year, month, today).getDay();

    let dayCount = today;
    let prevDayCount = today - firstDay;

    for (let i = 0; i < 6; i++) {
        calendarHTML += '<tr>';

        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                calendarHTML += `<td class="mute">${prevDayCount}</td>`;
                prevDayCount++;
            } else if (dayCount > daysInMonth) {
                let nextMonthDayCount = dayCount - daysInMonth;
                calendarHTML += `<td class="mute">${nextMonthDayCount}</td>`;
                dayCount++;
            } else {
                // 今日の日付にclassを付ける
                if (dayCount === today && month === currentMonth) {
                    calendarHTML += `<td class="today"><a href="/user/inputReserveView/${year}/${month+1}/${dayCount}" id="${year}${month+1}${dayCount}">${dayCount}</a></td>`;
                } 
                // 月曜日にclassを付ける
                else if (j === 1) {
                    calendarHTML += `<td class="off"><a href="/user/inputReserveView/${year}/${month+1}/${dayCount}" id="${year}${month+1}${dayCount}">${dayCount}</a></td>`;
                } else {
                    calendarHTML += `<td><a href="/user/inputReserveView/${year}/${month+1}/${dayCount}" id="${year}${month+1}${dayCount}">${dayCount}</a></td>`;
                }
                dayCount++;
            }
        }

        calendarHTML += '</tr>';

        if (dayCount - today > 7) {
            break;
        }
    }

    calendarHTML += '</tbody></table>';

    return calendarHTML;
}

document.getElementById('calendar').innerHTML = createCalendar(currentMonth, currentYear);