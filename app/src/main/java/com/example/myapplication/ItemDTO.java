package com.example.myapplication;

public class ItemDTO {  //공지사항 데이터(DB에서 불러온 데이터 저장)
    String title;
    String date;
    String content;

    public ItemDTO(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    @Override
    public String toString() {

        return "ItemDTO{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", qContent='" + content + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
