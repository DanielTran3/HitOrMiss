package com.dakinc.hitormiss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import clarifai2.dto.prediction.Concept;

public class CardCollection {
    private static String[] cardNames = new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static String[] cardPictureURL = new String[]{
            //Ace
            "http://i.imgur.com/mMauBa7.jpg",
            "http://i.imgur.com/pfO2UDP.jpg",
            "http://i.imgur.com/Lry5Owa.jpg",
            "http://i.imgur.com/83odqc0.jpg",
            //2
            "http://i.imgur.com/RkKKt0a.jpg",
            "http://i.imgur.com/CfqMo93.jpg",
            "http://i.imgur.com/k7yg4tF.jpg",
            "http://i.imgur.com/uy8jIkB.jpg",
            //3
            "http://i.imgur.com/GWfbdP8.jpg",
            "http://i.imgur.com/4AM7WWJ.jpg",
            "http://i.imgur.com/XeTAxVj.jpg",
            "http://i.imgur.com/f4eSDSD.jpg",
            //4
            "http://i.imgur.com/xqlw3Jh.jpg",
            "http://i.imgur.com/7T2VdJZ.jpg",
            "http://i.imgur.com/57Yf3D3.jpg",
            "http://i.imgur.com/DbHdeGP.jpg",
            //5
            "http://i.imgur.com/WArFEr6.jpg",
            "http://i.imgur.com/JxGYscW.jpg",
            "http://i.imgur.com/Jzpwd80.jpg",
            "http://i.imgur.com/DGNFbSC.jpg",
            //6
            "http://i.imgur.com/rvRuK0u.jpg",
            "http://i.imgur.com/eCXR4zG.jpg",
            "http://i.imgur.com/RKOS2bW.jpg",
            "http://i.imgur.com/zQhAKMJ.jpg",
            //7
            "http://i.imgur.com/p30ZONU.jpg",
            "http://i.imgur.com/tABfbt7.jpg",
            "http://i.imgur.com/x5Z4N94.jpg",
            "http://i.imgur.com/6R8ILys.jpg",
            //8
            "http://i.imgur.com/KPclcjM.jpg",
            "http://i.imgur.com/pJB8QTQ.jpg",
            "http://i.imgur.com/mpurY5M.jpg",
            "http://i.imgur.com/7l7zy05.jpg",
            //9
            "http://i.imgur.com/Hlq8P3U.jpg",
            "http://i.imgur.com/1wviHNg.jpg",
            "http://i.imgur.com/ceUK3eq.jpg",
            "http://i.imgur.com/ch3NsFa.jpg",
            //10
            "http://i.imgur.com/gSNTePl.jpg",
            "http://i.imgur.com/vW64mUL.jpg",
            "http://i.imgur.com/yL1ne3L.jpg",
            "http://i.imgur.com/H7WZ2Aw.jpg",
            //JAck
            "http://i.imgur.com/knWVerE.jpg",
            "http://i.imgur.com/9B9K60y.jpg",
            "http://i.imgur.com/dUaMHjt.jpg",
            "http://i.imgur.com/p57QBAl.jpg",
            //Queen
            "http://i.imgur.com/zyn40Sd.jpg",
            "http://i.imgur.com/BK3fD5k.jpg",
            "http://i.imgur.com/agEV4eF.jpg",
            "http://i.imgur.com/PyE7U8d.jpg",
            //King
            "http://i.imgur.com/Ude22io.jpg",
            "http://i.imgur.com/SWUpDx6.jpg",
            "http://i.imgur.com/6oj44Jl.jpg",
            "http://i.imgur.com/tua9ify.jpg",
    };

    public static String[] getListOfCardConcepts() {
        return cardNames;
    }

    public static String[] getListOfCardPictureURL() {
        return cardPictureURL;
    }
}
