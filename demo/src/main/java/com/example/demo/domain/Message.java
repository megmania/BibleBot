package com.example.demo.domain;

import lombok.Data;

/**
 * @author Myouhgwhan Lee on 2017. 2. 14..
 */
@Data
public class Message {

    String text; // the message to send to user.(max 100)
    Photo photo; // Image's info in ballon.( jpg|png )
    MessageButton message_button; //linkbutton info

}
