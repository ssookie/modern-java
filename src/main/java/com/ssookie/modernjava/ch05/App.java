package com.ssookie.modernjava.ch05;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {
    public static void main(String[] args) {
        /**
         * Type Safe 하지 않음
         */
        // params로 int 받는데, 11월은 public static final int NOVEMBER = 10; 임
        // Calendar ssookieBirthday = new GregorianCalendar(1992, 11, 18); -> 12월 의미
        Calendar ssookieBirthday = new GregorianCalendar(1992, Calendar.NOVEMBER, 18);
        System.out.println(ssookieBirthday.getTime());
        ssookieBirthday.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(ssookieBirthday.getTime());

        /**
         * 1. machine time 표현하기
         */
        System.out.println("\n=== 1. machine time 표현하기 ===");
        Instant instant = Instant.now();
        System.out.println(instant);    // 기준시 UTC, GMT

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        System.out.println(instant.atZone(zone));

        /**
         * 2. human time 표현하기
         */
        System.out.println("\n=== 2. human time 표현하기 ===");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime birthDay = LocalDateTime.of(1992, Month.NOVEMBER, 18, 0, 0, 0);
        System.out.println(birthDay);
        ZonedDateTime nowInLA = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(nowInLA);

        // Instant, ZonedDateTime 변환 가능
        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime);

        /**
         * 3. 기간 표현하기
         */
        System.out.println("\n=== 3. 기간 표현하기 ===");

        // Period - human time용 비교
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.NOVEMBER, 18);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.MONTHS));

        // Duration - machine time용 비교, instant 기준
        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now1, plus);
        System.out.println(between.getSeconds());

        /**
         * 4. 파싱/포매팅
         */
        System.out.println("\n=== 4. 파싱/포매팅 ===");
        
        // 포맷: 날짜 -> 문자
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy));

        // 파싱: 날짜 -> 문자
        LocalDate parse = LocalDate.parse("11/18/1992", MMddyyyy);
        System.out.println(parse);

        /**
         * 5. 레거시 API 지원
         */

        ZoneId newZoneAPI = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone legacyZoneAPI = TimeZone.getTimeZone(newZoneAPI);

        Instant newInstant = new Date().toInstant();
        Date legacyInstant = Date.from(newInstant);

    }
}
