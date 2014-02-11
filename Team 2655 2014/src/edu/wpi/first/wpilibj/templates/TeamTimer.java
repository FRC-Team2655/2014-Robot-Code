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
public class TeamTimer {
      public static void delay(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (final InterruptedException e) {
        }
    }
}
