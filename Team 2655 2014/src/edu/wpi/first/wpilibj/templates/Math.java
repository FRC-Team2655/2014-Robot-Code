/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author magic_000
 */
public class Math {

    // a table of sin(deg)
    static double sinx[] = {
        //sin(0), sin(1), sin(2), etc....sin(45)...
        0, 0.017452406, 0.034899497, 0.052335956, 0.069756474, 0.087155743, 0.104528463, 0.121869343, 0.139173101, 0.156434465, 0.173648178, 0.190808995, 0.207911691, 0.224951054, 0.241921896, 0.258819045, 0.275637356, 0.292371705, 0.309016994, 0.325568154, 0.342020143, 0.35836795, 0.374606593, 0.390731128, 0.406736643, 0.422618262, 0.438371147, 0.4539905, 0.469471563, 0.48480962, 0.5, 0.515038075, 0.529919264, 0.544639035, 0.559192903, 0.573576436, 0.587785252, 0.601815023, 0.615661475, 0.629320391, 0.64278761, 0.656059029, 0.669130606, 0.68199836, 0.69465837, 0.707106781, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };

    // we only support up to about 45 deg
    public double deg(double tsinx) {
        if (tsinx >= 1.0) {
            return 90.0;
        }

        for (int i = 0; i < sinx.length; i++) {
            if (tsinx < sinx[i]) {
                return i;
            }
        }
        return 0.0;
    }

    public double rad(double sinx) {
        return java.lang.Math.toRadians(deg(sinx));
    }

}
