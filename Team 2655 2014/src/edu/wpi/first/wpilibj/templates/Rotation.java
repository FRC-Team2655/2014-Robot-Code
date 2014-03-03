/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Zephan(:D)
 */
public class Rotation {

    DriveSystem driveSystem;

    Rotation() {
        driveSystem = new DriveSystem();
    }

    public void faceForward() {
        driveSystem.rotateToDegree(0);
    }

    public void faceRight() {
        driveSystem.rotateToDegree(90);
    }

    public void faceBackwards() {
        driveSystem.rotateToDegree(180);
    }

    public void faceLeft() {
        driveSystem.rotateToDegree(270);
    }

}
