package com.techacademy;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KadaiFirstController {

    @GetMapping("/")
    public String index() {
        return "Hello SpringBoot!";
    }

    /**
     * 指定日の曜日を算定します。
     * @param yyyymmdd
     * @return 指定日の曜日
     */
    @GetMapping("/dayofweek/{yyyymmdd}")
    public String dispDayOfWeek(@PathVariable String yyyymmdd) {
        int year, month, day;

        try {
            // 日付文字列から年・月・日を取得する
            year = Integer.parseInt(yyyymmdd.substring(0, 4));
            month = Integer.parseInt(yyyymmdd.substring(4, 6));
            day = Integer.parseInt(yyyymmdd.substring(6, 8));

            // 日付の有効性チェック
            LocalDate date = LocalDate.of(year, month, day);
        } catch (NumberFormatException e) {
            return "変換できる数値形式の文字列ではありません。";
        } catch (DateTimeException e) {
            return "入力された年月日は不正です。";
        }

        // Calendar インスタンスの作成
        Calendar calendar = Calendar.getInstance();
        // 年・月・日を設定
        calendar.set(year, month - 1, day);
        // 曜日の取得
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 曜日の数値を曜日の名前に変換
        String[] dayNames = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String dayName = dayNames[dayOfWeek];

        return dayName;
    }

    /**
     * 四則演算を行います。
     * @param val1
     * @param val2
     * @return 実行結果
     */
    @GetMapping("/plus/{val1}/{val2}")
    public String calcPlus(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 + val2;
        return "実行結果：" + res;
    }

    @GetMapping("/minus/{val1}/{val2}")
    public String calcMinus(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 - val2;
        return "実行結果：" + res;
    }

    @GetMapping("/times/{val1}/{val2}")
    public String calcTimes(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 * val2;
        return "実行結果：" + res;
    }

    @GetMapping("/divide/{val1}/{val2}")
    public String calcDivide(@PathVariable int val1, @PathVariable int val2) {
        int res = 0;
        res = val1 / val2;
        return "実行結果：" + res;
    }

}
