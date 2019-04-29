package br.com.salescenter.contants.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.salescenter.contants.enumeration.SegmentEnum;

@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	private static Map<SegmentEnum, String> entities = new WeakHashMap<SegmentEnum, String>();

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		synchronized (entities) {
			if (!entities.containsKey(entity)) {
				String uuid = UUID.randomUUID().toString();
				entities.put((SegmentEnum) entity, uuid);
				return uuid;
			} else {
				return entities.get(entity);
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
		for (Entry<SegmentEnum, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(uuid)) { return entry.getKey(); }
		}
		return null;
	}
}