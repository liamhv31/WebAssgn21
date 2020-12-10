/**
 * This class converts color objects to strings and vice versa
 * 
 * @author Liam Henley-Vachon
 * @version 1
 * @since 11/22/2020
 */
package service;
import java.awt.Color;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("ccno")
public class ColorConverter implements Converter {
  
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Color.decode("#"+value);
    }
   
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Color color = (Color) value;
        return Integer.toHexString(color.getRGB()).substring(2);
    }
}