/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author magic_000
 */
/*

class not used anymore -- using MathUtils.atan()

public class OurMath {

    // a table of sin(deg)
    //double sin[] = {
    //sin(0), sin(1), sin(2), etc....sin(45)...
    //0, 0.017452406, 0.034899497, 0.052335956, 0.069756474, 0.087155743, 0.104528463, 0.121869343, 0.139173101, 0.156434465, 0.173648178, 0.190808995, 0.207911691, 0.224951054, 0.241921896, 0.258819045, 0.275637356, 0.292371705, 0.309016994, 0.325568154, 0.342020143, 0.35836795, 0.374606593, 0.390731128, 0.406736643, 0.422618262, 0.438371147, 0.4539905, 0.469471563, 0.48480962, 0.5, 0.515038075, 0.529919264, 0.544639035, 0.559192903, 0.573576436, 0.587785252, 0.601815023, 0.615661475, 0.629320391, 0.64278761, 0.656059029, 0.669130606, 0.68199836, 0.69465837, 0.707106781
    //};
    // a table of tan(deg)
    static double tan[] = {
        0, 0.017455065, 0.034920769, 0.052407779, 0.069926812, 0.087488664, 0.105104235, 0.122784561, 0.140540835, 0.15838444, 0.176326981, 0.194380309, 0.212556562, 0.230868191, 0.249328003, 0.267949192, 0.286745386, 0.305730681, 0.324919696, 0.344327613, 0.363970234, 0.383864035, 0.404026226, 0.424474816, 0.445228685, 0.466307658, 0.487732589, 0.509525449, 0.531709432, 0.554309051, 0.577350269, 0.600860619, 0.624869352, 0.649407593, 0.674508517, 0.700207538, 0.726542528, 0.75355405, 0.781285627, 0.809784033, 0.839099631, 0.869286738, 0.900404044, 0.932515086, 0.965688775, 1.0

    };

    // returns arc tangent in radians
    // the degree is the index in the tan array above where we found the tangent of the angle
    // (this function seems to be available in
    //      com.sun.squawk.util.MathUtils.atan(angdeg)
    // but not supported on this java platform. IDK. We write our own.
    public static double atan(double tangentOfAngle) {
        int originalSignOfTangentOfAngle = 1;             // assume positive

        double answerInDegrees = (tan.length - 1) * originalSignOfTangentOfAngle;

        if (tangentOfAngle < 0) {
            originalSignOfTangentOfAngle = -1;
            tangentOfAngle = -tangentOfAngle;
        }

        if (tangentOfAngle >= 1.0) {
            // we don't do anything more than 45 degrees
            // so just return the highest degree value
            answerInDegrees = (tan.length - 1) * originalSignOfTangentOfAngle;
        } else {
            // search the tan array for a value
            for (int i = 0; i < tan.length; i++) {
                if (tangentOfAngle >= tan[i]) {
                    if (i == 0) {
                        answerInDegrees = 0.0;
                        break;
                    }
                    // we could interpolate between this and
                    // the last array entry for a little better accuracy.
                    answerInDegrees = --i * originalSignOfTangentOfAngle;
                    break;
                }
            }
        }
        return java.lang.Math.toRadians(answerInDegrees);
    }
}
*/