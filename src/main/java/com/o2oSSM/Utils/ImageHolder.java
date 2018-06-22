package com.o2oSSM.Utils;

import lombok.Data;

import java.io.InputStream;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/13
 * 11:27
 * #
 */
@Data
public class ImageHolder {

    private InputStream inputStream;

    private String inputStreamName;

    public ImageHolder() {
    }

    public ImageHolder(InputStream inputStream, String inputStreamName) {
        this.inputStream = inputStream;
        this.inputStreamName = inputStreamName;
    }
}
