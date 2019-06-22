/*
  JWildfire - an image and animation processor written in Java
  Copyright (C) 1995-2019 Andreas Maschke

  This is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
  General Public License as published by the Free Software Foundation; either version 2.1 of the
  License, or (at your option) any later version.

  This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License along with this software;
  if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
  02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jwildfire.create.tina.swing;

import org.jwildfire.base.Tools;
import org.jwildfire.create.tina.base.XForm;
import org.jwildfire.create.tina.base.weightingfield.CellularNoiseDistanceFunction;
import org.jwildfire.create.tina.base.weightingfield.CellularNoiseReturnType;

import javax.swing.*;

public class CellularNoiseWeightingFieldControlsUpdater extends WeightingFieldControlsUpdater {
  private boolean fillingComboBox;

  public CellularNoiseWeightingFieldControlsUpdater(TinaController controller, TinaWeightingFieldControllerData controls) {
    super(controller, controls);
  }

  @Override
  public void weightingFieldColorMapFilenameBtn_clicked() {
    // EMPTY
  }

  @Override
  public void updateControls(XForm xform) {
    super.updateControls(xform);
    controls.weightingFieldParam01REd.setMinValue(1);
    controls.weightingFieldParam01REd.setHasMinValue(true);
    controls.weightingFieldParam01REd.setOnlyIntegers(true);
    controls.weightingFieldParam01REd.setText(String.valueOf(xform.getWeightingFieldNoiseSeed()));

    controls.weightingFieldParam04Cmb.setSelectedItem(xform.getWeightingFieldCellularNoiseReturnType());

    controls.weightingFieldParam05REd.setHasMinValue(false);
    controls.weightingFieldParam05REd.setOnlyIntegers(false);
    controls.weightingFieldParam05REd.setText(Tools.doubleToString(xform.getWeightingFieldNoiseFrequency()));

    controls.weightingFieldParam08Cmb.setSelectedItem(xform.getWeightingFieldCellularNoiseDistanceFunction());
  }

  @Override
  public void enableControls(XForm xform, boolean enabled) {
    super.enableControls(xform, enabled);

    controls.weightingFieldColorMapFilenameLbl.setVisible(false);
    controls.weightingFieldColorMapFilenameBtn.setVisible(false);
    controls.weightingFieldColorMapFilenameInfoLbl.setVisible(false);

    controls.weightingFieldParam01REd.setVisible(true);
    controls.weightingFieldParam01REd.setEnabled(enabled);
    controls.weightingFieldParam01Lbl.setVisible(true);
    controls.weightingFieldParam01Lbl.setText("Seed");

    controls.weightingFieldParam02REd.setVisible(false);
    controls.weightingFieldParam02Lbl.setVisible(false);

    controls.weightingFieldParam03REd.setVisible(false);
    controls.weightingFieldParam03Lbl.setVisible(false);

    controls.weightingFieldParam04Cmb.setVisible(true);
    controls.weightingFieldParam04Cmb.setEnabled(enabled);
    controls.weightingFieldParam04Lbl.setVisible(true);
    controls.weightingFieldParam04Lbl.setText("Return Type");
    fillingComboBox = true;
    try {
      controls.weightingFieldParam04Cmb.removeAllItems();
      controls.weightingFieldParam04Cmb.addItem(CellularNoiseReturnType.DISTANCE);
      controls.weightingFieldParam04Cmb.addItem(CellularNoiseReturnType.DISTANCE2);
      controls.weightingFieldParam04Cmb.addItem(CellularNoiseReturnType.DISTANCE_ADD);
      controls.weightingFieldParam04Cmb.addItem(CellularNoiseReturnType.DISTANCE_SUB);
      controls.weightingFieldParam04Cmb.addItem(CellularNoiseReturnType.DISTANCE_MUL);
      controls.weightingFieldParam04Cmb.addItem(CellularNoiseReturnType.DISTANCE_DIV);
    }
    finally {
      fillingComboBox = false;
    }

    controls.weightingFieldParam05REd.setVisible(true);
    controls.weightingFieldParam05REd.setEnabled(true);
    controls.weightingFieldParam05Lbl.setVisible(true);
    controls.weightingFieldParam05Lbl.setText("Frequency");

    controls.weightingFieldParam06REd.setVisible(false);
    controls.weightingFieldParam06Lbl.setVisible(false);

    controls.weightingFieldParam07REd.setVisible(false);
    controls.weightingFieldParam07Lbl.setVisible(false);

    controls.weightingFieldParam08Cmb.setVisible(true);
    controls.weightingFieldParam08Cmb.setEnabled(enabled);
    controls.weightingFieldParam08Lbl.setVisible(true);
    controls.weightingFieldParam08Lbl.setText("Distance Fnct");
    fillingComboBox = true;
    try {
      controls.weightingFieldParam08Cmb.removeAllItems();
      controls.weightingFieldParam08Cmb.addItem(CellularNoiseDistanceFunction.EUCLIDIAN);
      controls.weightingFieldParam08Cmb.addItem(CellularNoiseDistanceFunction.MANHATTAN);
      controls.weightingFieldParam08Cmb.addItem(CellularNoiseDistanceFunction.NATURAL);
    }
    finally {
      fillingComboBox = false;
    }
  }

  @Override
  public void weightingFieldParam01REd_changed() {
    controller.xFormTextFieldChanged(null, controls.weightingFieldParam01REd, "weightingFieldNoiseSeed", 1.0);
  }

  @Override
  public void weightingFieldParam02REd_changed() {
    // EMPTY
  }

  @Override
  public void weightingFieldParam03REd_changed() {
    // EMPTY
  }

  @Override
  public void weightingFieldParam04Cmb_changed() {
    if (controller.gridRefreshing || fillingComboBox)
      return;
    XForm xForm = controller.getCurrXForm();
    if (xForm != null && controls.weightingFieldParam04Cmb.getSelectedItem() != null) {
      xForm.setWeightingFieldCellularNoiseReturnType((CellularNoiseReturnType) controls.weightingFieldParam04Cmb.getSelectedItem());
      controller.xFormControls.enableControls(xForm);
      controller.refreshXFormUI(xForm);
      controller.refreshFlameImage(true, false, 1, true, false);
    }
  }

  @Override
  public void weightingFieldParam05REd_changed() {
    controller.xFormTextFieldChanged(null, controls.weightingFieldParam05REd, "weightingFieldNoiseFrequency", 1.0);
  }

  @Override
  public void weightingFieldParam06REd_changed() {
    // EMPTY
  }

  @Override
  public void weightingFieldParam07REd_changed() {
    // EMPTY
  }

  @Override
  public void weightingFieldParam08Cmb_changed() {
    if (controller.gridRefreshing || fillingComboBox)
      return;
    XForm xForm = controller.getCurrXForm();
    if (xForm != null && controls.weightingFieldParam08Cmb.getSelectedItem() != null) {
      xForm.setWeightingFieldCellularNoiseDistanceFunction((CellularNoiseDistanceFunction) controls.weightingFieldParam08Cmb.getSelectedItem());
      controller.xFormControls.enableControls(xForm);
      controller.refreshXFormUI(xForm);
      controller.refreshFlameImage(true, false, 1, true, false);
    }
  }
}
