package gui.pages;

import gui.WizardPage;
import gui.model.ConfigData;
import gui.model.ErrorMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author FBM
 *
 */
public class CrossValidationWizard extends WizardPage {

	private Label numberOfFolds;
	private Slider slider;
	private Label numberOfFoldsValue;

	private CheckBox randomizationSeedCheckBox;
	private TextField randomizationSeedTextField;

	private ErrorMessage errorMessage;

	/**
	 * @param title
	 */
	public CrossValidationWizard() {
		super("Cross Validation setting");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.WizardPage#getContent()
	 */
	@Override
	public Parent getContent() {
		initFoldNumberAttributes();
		initRandimizationSeedAttributes();
		initErrorLabel();
		return initLayout();
	}

	/**
	 * 
	 */
	private void initErrorLabel() {
		errorMessage = new ErrorMessage();
	}

	/**
	 * 
	 */
	private void initRandimizationSeedAttributes() {
		randomizationSeedCheckBox = new CheckBox("Set Randomization Seed?");
		randomizationSeedTextField = new TextField();
		randomizationSeedTextField.setDisable(true);

		randomizationSeedCheckBox.selectedProperty()
				.addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
					randomizationSeedTextField.setDisable(!newValue);
					if (!newValue) {
						randomizationSeedTextField.setText("");
						errorMessage.setText("");
					}
					final StringProperty textProperty = randomizationSeedTextField.textProperty();
					ConfigData.instance.RANDOMIZATION_SEED.bind(textProperty);
				});
	}

	/**
	 * @return
	 */
	private Parent initLayout() {
		final GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);

		gridpane.add(numberOfFolds, 0, 0);
		gridpane.add(slider, 1, 0);

		gridpane.add(randomizationSeedCheckBox, 0, 1);
		gridpane.add(randomizationSeedTextField, 1, 1);

		final HBox hBox = new HBox(5.0,gridpane,numberOfFoldsValue);
		final VBox mainLayout = new VBox(5.0, hBox, errorMessage);
		return mainLayout;
	}

	/**
	 * 
	 */
	private void initFoldNumberAttributes() {
		numberOfFolds = new Label("Number of folds");
		numberOfFoldsValue = new Label();
		numberOfFoldsValue.setFont(new Font("Arial", 30));

		slider = new Slider();
		slider.setMin(0);
		slider.setMax(50);
		slider.setValue(5);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(5);
		slider.setBlockIncrement(1);
		slider.setPrefWidth(300);
		numberOfFoldsValue.setText(String.valueOf((int) slider.getValue()));
		ConfigData.instance.NUMBER_OF_FOLDS.bind(new SimpleStringProperty(String.valueOf((int) slider.getValue())));
		slider.valueProperty().addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
			numberOfFoldsValue.setText(String.valueOf((int) slider.getValue()));
			ConfigData.instance.NUMBER_OF_FOLDS.bind(new SimpleStringProperty(String.valueOf((int) slider.getValue())));
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.WizardPage#validate()
	 */
	@Override
	public boolean validate() {
		if (randomizationSeedCheckBox.isSelected()) {
			try {
				Double.parseDouble(randomizationSeedTextField.getText());
				errorMessage.setText("");
				return true;
			} catch (final Exception exception) {
				errorMessage.setText("Seed is not valid");
				return false;
			}
		} else {
			return true;
		}
	}

}
