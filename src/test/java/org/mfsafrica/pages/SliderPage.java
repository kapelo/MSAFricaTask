package org.mfsafrica.pages;

import org.openqa.selenium.By;

public class SliderPage extends BasePage {
    private final By sliderLocator = By.cssSelector(".range-slider");

    public void dragByValue(int value) {
        dragSlider(sliderLocator, value);
    }

    public int getSliderValueAttribute() {
        return Integer.parseInt(getElementAttribute(sliderLocator, "value"));
    }
}
