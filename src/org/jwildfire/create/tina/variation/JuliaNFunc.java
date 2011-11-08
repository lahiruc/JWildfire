/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2011 Andreas Maschke

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
package org.jwildfire.create.tina.variation;

import org.jwildfire.create.tina.base.XForm;
import org.jwildfire.create.tina.base.XYZPoint;

public class JuliaNFunc extends VariationFunc {

  private static final String PARAM_POWER = "power";
  private static final String PARAM_DIST = "dist";
  private static final String[] paramNames = { PARAM_POWER, PARAM_DIST };

  private int power = 2;
  private double dist = 1;

  private double sqr(double pVal) {
    return pVal * pVal;
  }

  @Override
  public void transform(TransformationContext pContext, XForm pXForm, XYZPoint pAffineTP, XYZPoint pVarTP, double pAmount) {
    double rnd = pContext.getRandomNumberGenerator().random();
    double angle = (Math.atan2(pAffineTP.y, pAffineTP.x) + 2 * Math.PI * ((int) (rnd * Math.abs(power)))) / (double) power;
    double sina = Math.sin(angle);
    double cosa = Math.cos(angle);
    double d = dist / (power + power);
    double r = pAmount * Math.pow(sqr(pAffineTP.x) + sqr(pAffineTP.y), d);
    pVarTP.x += r * cosa;
    pVarTP.y += r * sina;
  }

  @Override
  public String[] getParameterNames() {
    return paramNames;
  }

  @Override
  public Object[] getParameterValues() {
    return new Object[] { power, dist };
  }

  @Override
  public void setParameter(String pName, double pValue) {
    if (PARAM_POWER.equalsIgnoreCase(pName))
      power = (int) (pValue + 0.5);
    else if (PARAM_DIST.equalsIgnoreCase(pName))
      dist = pValue;
    else
      throw new IllegalArgumentException(pName);
  }

  @Override
  public String getName() {
    return "julian";
  }

}
