package client.scenes;

import client.services.I18N;
import client.services.NotificationService;
import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InviteViewCtrl implements Initializable {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private final NotificationService notificationService;
    @FXML
    public Text eventTitle;
    @FXML
    public Text eventCode;
    @FXML
    public Button copyToClipboardBtn;
    @FXML
    public TextArea textArea;
    @FXML
    public Button sendInviteBtn;
    @FXML
    public Button cancelBtn;
    @FXML
    public Labeled inviteInstr;
    @FXML
    public Labeled emailLabel;
    private Event event;


    @Inject
    public InviteViewCtrl(ServerUtils server, MainCtrl mainCtrl, NotificationService notificationService) {
        this.server = server;
        this.mainCtrl = mainCtrl;
        this.notificationService = notificationService;
        this.event=new Event();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.copyToClipboardBtn.setOnAction(event -> copyToClipboard());
        this.sendInviteBtn.setOnAction(event -> sendInvite());
        this.cancelBtn.setOnAction(event -> backToEvent());
    }

    public void refresh() {
        I18N.update(copyToClipboardBtn);
        I18N.update(inviteInstr);
        I18N.update(emailLabel);
        I18N.update(sendInviteBtn);
        I18N.update(cancelBtn);
    }

    private void sendInvite() {
        if (textArea.getText().isEmpty()) {
            notificationService.showError("No addresses", "Please enter at least one address");
            return;
        }
        List<String> addresses = List.of(textArea.getText().split("\n"));
        backToEvent();
    }

    private void copyToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(eventCode.getText());
        clipboard.setContent(content);
    }

    public void setEvent(Event newEvent){
        this.event=newEvent;
        eventTitle.setText(this.event.getTitle());
        eventCode.setText(this.event.getInviteCode());

    }
    public void backToEvent(){
        mainCtrl.showEventOverviewScene(event);
    }
}
