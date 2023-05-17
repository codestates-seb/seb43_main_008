package com.ssts.ssts.domain.bookmark.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookmarkResponse {
    //Serise: seriseId, title, daylogCount, image

    Long seriseId;
    String titile;
    int daylogCount;
    String image;

    public static BookmarkResponse of(Long seriseId, String title, int daylogCount, String image){
        BookmarkResponse bookmarkResponse = new BookmarkResponse();

        bookmarkResponse.setSeriseId(seriseId);
        bookmarkResponse.setTitile(title);
        bookmarkResponse.setDaylogCount(daylogCount);
        bookmarkResponse.setImage(image);
        return bookmarkResponse;
    }


}
