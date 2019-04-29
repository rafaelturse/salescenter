package br.com.salescenter.controller.compliment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.salescenter.contants.enumeration.SegmentEnum;
import br.com.salescenter.controller.base.BaseCRUD;
import br.com.salescenter.orm.client.ClientORM;
import br.com.salescenter.orm.compliment.SegmentORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "segmentController")
@ViewScoped
public class SegmentController extends BaseCRUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private DualListModel<SegmentEnum> dualSegments;
	private List<SegmentEnum> source = new ArrayList<SegmentEnum>();
	private List<SegmentEnum> target = new ArrayList<SegmentEnum>();
	private List<SegmentORM> orms = new ArrayList<SegmentORM>();
	private SegmentEnum[] segments;

	@PostConstruct
	public void init() {
		segments = SegmentEnum.values();

		for (SegmentEnum i : segments) {
			source.add(i);
		}

		dualSegments = new DualListModel<SegmentEnum>(source, target);
	}

	public void initSelectedSegments() {
		for (SegmentORM i : orms) {
			dualSegments.getSource().remove(i.getSegment());
			dualSegments.getTarget().add(i.getSegment());
		}
	}

	public List<SegmentORM> prepareORMs(final ClientORM client) throws Exception {
		orms = new ArrayList<SegmentORM>();
		
		for (int i = 0; i < dualSegments.getTarget().size(); i++) {
			SegmentORM orm = new SegmentORM();

			orm.setClient(client);
			orm.setSegment(SegmentEnum.valueOfId(dualSegments.getTarget().get(i).getId()));
			orms.add(orm);
		}

		return orms;
	}
}
