package com.practicaljava.lesson30.ui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.scene.layout.Stack;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.TextBoundsType;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import com.practicaljava.lesson30.jms.Messages;
import com.practicaljava.lesson30.jms.MessageHandler;

class JavaFXMessageHandler extends MessageHandler {
    override public function handle(message:String):Void {
        FX.deferAction( function() { addNewText(message) } );
    }
}

Messages.startListening(new JavaFXMessageHandler());

var oldText = "";
var newText = "";

var oldTextOpacity = 0.0;
var oldTextYShift = -40.0;

var font = Font {
    name: "Verdana"
    size: 16
}

var oldTextLabel:Label = Label {
    font: font
    text: bind oldText
    textFill: Color.web("#808020")
    translateY: bind oldTextYShift
    opacity: bind oldTextOpacity
}

var newTextOpacity = 0.0;
var newTextYShift = 40.0;

var newTextLabel:Label = Label {
    textAlignment: TextAlignment.CENTER
    font: font
    textFill: Color.web("#808020")
    text: bind newText
    translateY: bind newTextYShift
    opacity: bind newTextOpacity;
}

var oldTextAnimation = Timeline {
    repeatCount: 1
    keyFrames: [
        at (0s) {
            oldTextOpacity => 1.0 tween Interpolator.LINEAR;
            oldTextYShift => 0.0 tween Interpolator.LINEAR;
        }

        at (1s) {
            oldTextOpacity => 0.0 tween Interpolator.LINEAR;
            oldTextYShift => -40.0 tween Interpolator.LINEAR;
        }
    ];
}

var newTextAnimation = Timeline {
    repeatCount: 1
    keyFrames: [
        at (0s) {
            newTextOpacity => 0.0 tween Interpolator.LINEAR;
            newTextYShift => 40.0 tween Interpolator.LINEAR;
        }

        at (.5s) {
            newTextOpacity => 1.0 tween Interpolator.LINEAR;
            newTextYShift => 0.0 tween Interpolator.LINEAR;
        }
    ];
}

function addNewText(text: String): Void {
    oldText = newText;
    newText = text;

    oldTextAnimation.playFromStart();
    newTextAnimation.playFromStart();
}


Stage {
    title: "Application title"

    scene: Scene {
        width: 600
        height: 100

        content: VBox {
            content: [
                Stack {
                    nodeHPos: HPos.CENTER
                    content: [
                        Rectangle {
                            width: 600
                            height: 80
                            opacity: 0.3
                            fill: LinearGradient {
                                startX: 0, startY: 0.0, endX: 0, endY: 80.0
                                proportional: false
                                stops: [
                                    Stop { offset: 0.0 color: Color.web("#303030") }
                                    Stop { offset: 1.0 color: Color.web("#FFFFFF") }
                                ]
                            }
                        },
                        oldTextLabel,
                        newTextLabel ]
                }]
        }
    }

    onClose : function() { Messages.stopListening(); }
}
