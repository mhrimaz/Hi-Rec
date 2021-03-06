package gui;

import gui.pages.AlgorithmWizard;
import gui.pages.DataSetWizard;
import gui.pages.FirstPage;
import gui.pages.GeneralFeatureWizard;
import gui.pages.ReviewWizard;
import gui.pages.RunPage;
import javafx.application.Platform;
import javafx.stage.Stage;

class WizardMaker extends Wizard {

	final Stage owner;

	public WizardMaker(final Stage owner) {
		super(new FirstPage(), new DataSetWizard() , new GeneralFeatureWizard(),
				new AlgorithmWizard(), new ReviewWizard(), new RunPage());
		this.owner = owner;
	}

	public void reset(){
		this.pages.forEach(p->p.reset());
	}
	
	public void fillWithSampleData(){
		this.pages.forEach(p->p.fillWithSampleData());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Wizard#cancel()
	 */
	@Override
	public void cancel() {
		owner.close();
		Platform.exit();
		System.exit(0);
	}
}
