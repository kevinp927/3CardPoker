

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThreeCardPokerGame extends Application {
	
	boolean playerOneFold;
	boolean playerTwoFold;
	
	Player playerOne; 
	Player playerTwo;
	Dealer theDealer; 
	
	Label  txtPlayer1Balance;
	Label  txtPlayer2Balance;
	
	TextField anteInputPlayer1;
	TextField anteInputPlayer2;
	
	Image backOfCard;
	
	ListView listView;
	
	boolean btnDealPressed;

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		launch(args);
		
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception { 
				
		boolean player1Play; // checks if the player 
		boolean player2Play;
		
		boolean player1Fold; // checks if the player folds
		boolean player2Fold;
				
		theDealer = new Dealer(); // calls constructors
		playerOne = new Player();
		playerTwo = new Player();

		ArrayList<ImageView> DealersCardsImages = new ArrayList<ImageView>(); // holds the imageviews for the cards
		ArrayList<ImageView> Player1CardsImages = new ArrayList<ImageView>();
		ArrayList<ImageView> Player2CardsImages = new ArrayList<ImageView>();
				
		primaryStage.setTitle("Welcome to Project #2");
		
		listView = new ListView(); // holds the info
		
		listView.setMaxHeight(180);
		listView.setMaxWidth(320);

		listView.getItems().add("Enter Your Bets");
		
		Menu m = new Menu("Options");
		
		MenuItem m1 = new MenuItem("Exit");
        MenuItem m2 = new MenuItem("Fresh Start");
        MenuItem m3 = new MenuItem("NewLook");
        
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);
        
        MenuBar mb = new MenuBar();
        mb.getMenus().add(m);
		
		Button btnDeal = new Button("DEAL");
				
		Image emptyCard = new Image("Emptyspace.png");
		Image image4 = new Image("3cardpokersign.png");
		backOfCard = new Image("red_back.png");
		
		ImageView IM1 = new ImageView();
		ImageView IM2 = new ImageView();
		ImageView IM3 = new ImageView();
		
		DealersCardsImages.add(IM1);
		DealersCardsImages.add(IM2);
		DealersCardsImages.add(IM3);
		
		ImageView IM4 = new ImageView();
		
		ImageView Player1Card1 = new ImageView();
		ImageView Player1Card2 = new ImageView();
		ImageView Player1Card3 = new ImageView();
		
		Player1CardsImages.add(Player1Card1);
		Player1CardsImages.add(Player1Card2);
		Player1CardsImages.add(Player1Card3);
				
		ImageView Player2Card1 = new ImageView();
		ImageView Player2Card2 = new ImageView();
		ImageView Player2Card3 = new ImageView();
		
		Player2CardsImages.add(Player2Card1);
		Player2CardsImages.add(Player2Card2);
		Player2CardsImages.add(Player2Card3);
		
		HBox DealerCards = new HBox();
		HBox PlayerOneCards = new HBox();
		HBox PlayerTwoCards = new HBox();
		HBox BothPlayerCards = new HBox();
		HBox BothPlayerGrids= new HBox();

		VBox Middle = new VBox();
		VBox menubar = new VBox();
		
		for(int i=0;i<DealersCardsImages.size();i++) { // sets the images to the imageview
			DealersCardsImages.get(i).setImage(emptyCard);
			DealersCardsImages.get(i).setFitHeight(200);
			DealersCardsImages.get(i).setFitWidth(130);
		}
		
		for(int i=0;i<Player1CardsImages.size();i++) {
			Player1CardsImages.get(i).setImage(emptyCard);
			Player1CardsImages.get(i).setFitHeight(190);
			Player1CardsImages.get(i).setFitWidth(120);
		}
		
		for(int i=0;i<Player2CardsImages.size();i++) {
			Player2CardsImages.get(i).setImage(emptyCard);
			Player2CardsImages.get(i).setFitHeight(190);
			Player2CardsImages.get(i).setFitWidth(120);
		}
	
		IM4.setImage(image4);
		IM4.setFitHeight(180);
		IM4.setFitWidth(280);	
		
		DealerCards.getChildren().add(IM1); // adds the value to the another container
		DealerCards.getChildren().add(IM2);
		DealerCards.getChildren().add(IM3);
		
		PlayerOneCards.getChildren().add(Player1Card1);
		PlayerOneCards.getChildren().add(Player1Card2);
		PlayerOneCards.getChildren().add(Player1Card3);
		
		PlayerTwoCards.getChildren().add(Player2Card1);
		PlayerTwoCards.getChildren().add(Player2Card2);
		PlayerTwoCards.getChildren().add(Player2Card3);
		
		Middle.getChildren().add(IM4);
		
		DealerCards.setAlignment(Pos.TOP_CENTER);
		Middle.setAlignment(Pos.TOP_CENTER);
		PlayerOneCards.setAlignment(Pos.BOTTOM_LEFT);
		PlayerTwoCards.setAlignment(Pos.BOTTOM_RIGHT);
		
		BothPlayerCards.getChildren().add(PlayerOneCards);
		BothPlayerCards.getChildren().add(btnDeal);
		BothPlayerCards.getChildren().add(PlayerTwoCards);

		BothPlayerCards.setAlignment(Pos.BOTTOM_CENTER);

		BothPlayerCards.setSpacing(110);
		DealerCards.setSpacing(20);
		PlayerOneCards.setSpacing(10);
		PlayerTwoCards.setSpacing(10);
		
		GridPane player1Grid = new GridPane(); // grid that holds the info for bets 
		//player1Grid.setHgap(5);
		player1Grid.setVgap(10);
		
		Label txtplayer1 = new Label("Player One");
		Label txtAntePlayer1 = new Label("Ante Wager: ");
		Label txtPairPlusPlayer1 = new Label("Pair Plus Wager: ");
		Label txtBalance1 = new Label("Total Winnings: ");
		txtPlayer1Balance = new Label("0");
		
		anteInputPlayer1 = new TextField();
		TextField pairplusInputPlayer1 = new TextField();
		
		Button playPlayer1 = new Button("PLAY");
		Button foldPlayer1 = new Button("FOLD");
		
		playPlayer1.setDisable(true);
		foldPlayer1.setDisable(true);
		
		playPlayer1.setVisible(false);
		foldPlayer1.setVisible(false);

		txtplayer1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20)); // sets the fonts
		txtAntePlayer1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		txtPairPlusPlayer1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		playPlayer1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		foldPlayer1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15 ));
		txtBalance1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		txtPlayer1Balance.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		txtplayer1.setStyle("-fx-text-fill: gold");
		txtAntePlayer1.setStyle("-fx-text-fill: gold");
		txtPairPlusPlayer1.setStyle("-fx-text-fill: gold");
		playPlayer1.setStyle("-fx-text-fill: black");
		foldPlayer1.setStyle("-fx-text-fill: black");
		txtBalance1.setStyle("-fx-text-fill: gold");
		txtPlayer1Balance.setStyle("-fx-text-fill: gold");
		
		playPlayer1.setStyle("-fx-background-color: gold; ");
		foldPlayer1.setStyle("-fx-background-color: gold; ");

		player1Grid.add(txtplayer1, 0, 10);
		player1Grid.add(txtAntePlayer1, 0, 13);
		player1Grid.add(txtPairPlusPlayer1, 0, 16);
		
		player1Grid.add(anteInputPlayer1, 1, 13);
		player1Grid.add(pairplusInputPlayer1, 1, 16);
		
		player1Grid.add(playPlayer1, 0, 19);
		player1Grid.add(foldPlayer1, 2, 19);
		
		player1Grid.add(txtBalance1, 0, 22);
		player1Grid.add(txtPlayer1Balance, 1, 22);

	    
	    GridPane player2Grid = new GridPane();
	    player2Grid.setVgap(10);
		
		Label txtPlayer2 = new Label("Player Two");
		Label txtAntePlayer2 = new Label("Ante Wager: ");
		Label txtpairplusPlayer2 = new Label("Pair Plus Wager: ");
		Label txtBalance2 = new Label("Total Winnings: ");
	    txtPlayer2Balance = new Label("0");
		
		anteInputPlayer2 = new TextField();
		TextField pairplusInputPlayer2 = new TextField();
		
		Button playPlayer2 = new Button("PLAY");
		Button foldPlayer2 = new Button("FOLD");
		
		playPlayer2.setDisable(true);
		foldPlayer2.setDisable(true);

		playPlayer2.setVisible(false);
		foldPlayer2.setVisible(false);
		
		txtPlayer2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		txtAntePlayer2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		txtpairplusPlayer2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		playPlayer2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		foldPlayer2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		txtBalance2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		txtPlayer2Balance.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		txtPlayer2.setStyle("-fx-text-fill: gold");
		txtAntePlayer2.setStyle("-fx-text-fill: gold");
		txtpairplusPlayer2.setStyle("-fx-text-fill: gold");
		playPlayer2.setStyle("-fx-text-fill: black");
		foldPlayer2.setStyle("-fx-text-fill: black");
		txtBalance2.setStyle("-fx-text-fill: gold");
		txtPlayer2Balance.setStyle("-fx-text-fill: gold");
		
		playPlayer2.setStyle("-fx-background-color: gold; ");
		foldPlayer2.setStyle("-fx-background-color: gold; ");

		player2Grid.add(txtPlayer2, 0, 10);
		player2Grid.add(txtAntePlayer2, 0, 13);
		player2Grid.add(txtpairplusPlayer2, 0, 16);
		
		player2Grid.add(anteInputPlayer2, 1, 13);
		player2Grid.add(pairplusInputPlayer2, 1, 16);
		
		player2Grid.add(playPlayer2, 0, 19);
		player2Grid.add(foldPlayer2, 2, 19);
		
		player2Grid.add(txtBalance2, 0, 22);
		player2Grid.add(txtPlayer2Balance, 1, 22);
		
		BothPlayerGrids.getChildren().add(player1Grid);
		BothPlayerGrids.getChildren().add(listView);
		BothPlayerGrids.getChildren().add(player2Grid);
		
		BothPlayerGrids.setSpacing(50);
		
		BothPlayerGrids.setAlignment(Pos.CENTER);
	    
		BorderPane BP = new BorderPane();
		
		menubar.getChildren().add(mb);
		menubar.getChildren().add(DealerCards);

		BP.setTop(menubar);
		BP.setBottom(BothPlayerCards);
		BP.setCenter(BothPlayerGrids);

	    BorderPane.setAlignment(PlayerOneCards, Pos.BOTTOM_LEFT);
	    BorderPane.setAlignment(PlayerOneCards, Pos.BOTTOM_LEFT);
	    BorderPane.setAlignment(btnDeal, Pos.TOP_CENTER);
	    BorderPane.setAlignment(PlayerTwoCards, Pos.BOTTOM_RIGHT);
	    BorderPane.setAlignment(BothPlayerGrids, Pos.CENTER);
	    //BorderPane.setAlignment(listView, Pos.TOP_RIGHT);

	    
		PlayerOneCards.setPadding(new Insets(10,10,10,10));
		PlayerTwoCards.setPadding(new Insets(10,10,10,10));
		DealerCards.setTranslateY(5);
		IM4.setTranslateY(50);
		btnDeal.setTranslateY(-100);
		
		btnDeal.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		btnDeal.setStyle("-fx-text-fill: black");
		btnDeal.setStyle("-fx-background-color: gold; ");


        BackgroundFill background_fill = new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
	    
	    BP.setBackground(background);

	    VBox startingScrean = new VBox();
	    startingScrean.setAlignment(Pos.CENTER);
	    startingScrean.setBackground(background);


	    Button play = new Button("Play Game");	    

	    play.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
	    play.setStyle("-fx-text-fill: black");
	    play.setStyle("-fx-background-color: gold; ");

	    
	    startingScrean.getChildren().add(play);
	    
	    startingScrean.setSpacing(30);
	    
	    Scene scene1 = new Scene(startingScrean, 500,500);
	    
		primaryStage.setScene(scene1);
		primaryStage.show();

	
	    Scene scene2 = new Scene(BP, 1200,900);
	    primaryStage.show();
	    
	    play.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(scene2);
				primaryStage.show();
			}
		});
	    
	    btnDeal.setOnAction(new EventHandler<ActionEvent>() // handles the deal button click
		{
			@Override
			public void handle(ActionEvent event) {
				int player1anteinput;
				int player2anteinput;
				int player1pairplusinput;
				int player2pairplusinput;
				
				if(!isNumeric(anteInputPlayer1.getText()) || !isNumeric(anteInputPlayer2.getText())) {
					listView.getItems().add("Please Enter a Valid Input");
					return;
				}
				if(anteInputPlayer1.getText() == "") {
					player1anteinput = 0;
				}
				else {
					player1anteinput = Integer.parseInt(anteInputPlayer1.getText());
				}
				
				if(anteInputPlayer2.getText() == "") {
					player2anteinput = 0;
				}
				else {
					player2anteinput = Integer.parseInt(anteInputPlayer2.getText());

				}
				
				if(player1anteinput < 5 || player1anteinput > 25) { // checks if the input is within 5-25
					listView.getItems().add("Please Enter a number between 5 and 25");
					return;
				}
				
				if(player2anteinput < 5 || player2anteinput > 25) {
					listView.getItems().add("Please Enter a number between 5 and 25");
					return;
				}
				
				
				if(pairplusInputPlayer1.getText() == "") {
					player1pairplusinput = 0;
				}
				else {
					if(isNumeric(pairplusInputPlayer1.getText())) { // checks if the input is a number
						player1pairplusinput = Integer.parseInt(pairplusInputPlayer1.getText());
						if(player1pairplusinput < 5 || player1pairplusinput > 25) {
							listView.getItems().add("Please Enter a number between 5 and 25");
							return;
						}
					}
					else {
						listView.getItems().add("Please Enter a Valid Input");
						return;
					}
				}
				
				if(pairplusInputPlayer2.getText() == "") {
					player2pairplusinput = 0;
				}
				else {
					if(isNumeric(pairplusInputPlayer2.getText())) {
						player2pairplusinput = Integer.parseInt(pairplusInputPlayer2.getText());
						if(player2pairplusinput < 5 || player2pairplusinput > 25) {
							listView.getItems().add("Please Enter a number between 5 and 25");
							return;
						}
					}
					else {
						listView.getItems().add("Please Enter a Valid Input");
						return;
					}
				}
				
				
				if(anteInputPlayer1.getText() == "") {
					playerOne.anteBet = 0;
				}
				else {
					playerOne.anteBet = Integer.parseInt(anteInputPlayer1.getText());
					playerOne.totalWinnings -= playerOne.anteBet;
				}
				if(anteInputPlayer2.getText() == "") {
					playerTwo.anteBet = 0;
				}
				else {
					playerTwo.anteBet = Integer.parseInt(anteInputPlayer2.getText());
					playerTwo.totalWinnings -= playerTwo.anteBet;
				}
				
				if(pairplusInputPlayer1.getText() == "") {
					playerOne.pairPlusBet = 0;
				}
				else {
					playerOne.pairPlusBet = Integer.parseInt(pairplusInputPlayer1.getText());
					playerOne.totalWinnings -= playerOne.pairPlusBet;
				}
				if(pairplusInputPlayer2.getText() == "") {
					playerTwo.pairPlusBet = 0;
				}
				else {
					playerTwo.pairPlusBet = Integer.parseInt(pairplusInputPlayer2.getText());
					playerTwo.totalWinnings -= playerTwo.pairPlusBet;

				}
				
				theDealer.dealersHand = theDealer.dealHand(); // deals new hand
				playerOne.hand = theDealer.dealHand();
				playerTwo.hand = theDealer.dealHand();
				
				ArrayList<String> player1CardFileNames= new ArrayList<String>();
				ArrayList<String> player2CardFileNames= new ArrayList<String>();

				for(int i=0;i<DealersCardsImages.size();i++) {
					DealersCardsImages.get(i).setImage(backOfCard);
				}
				
				String player1card1 = String.valueOf(playerOne.hand.get(0).value) + String.valueOf(playerOne.hand.get(0).suit) + ".png";
				String player1card2 = String.valueOf(playerOne.hand.get(1).value) + String.valueOf(playerOne.hand.get(1).suit) + ".png";
				String player1card3 = String.valueOf(playerOne.hand.get(2).value) + String.valueOf(playerOne.hand.get(2).suit) + ".png";
				
				String player2card1 = String.valueOf(playerTwo.hand.get(0).value) + String.valueOf(playerTwo.hand.get(0).suit) + ".png";
				String player2card2 = String.valueOf(playerTwo.hand.get(1).value) + String.valueOf(playerTwo.hand.get(1).suit) + ".png";
				String player2card3 = String.valueOf(playerTwo.hand.get(2).value) + String.valueOf(playerTwo.hand.get(2).suit) + ".png";
				
				player1CardFileNames.add(player1card1);
				player1CardFileNames.add(player1card2);
				player1CardFileNames.add(player1card3);
				
				player2CardFileNames.add(player2card1);
				player2CardFileNames.add(player2card2);
				player2CardFileNames.add(player2card3);
				
				for(int i=0;i<player1CardFileNames.size();i++) {
					Image card = new Image(player1CardFileNames.get(i));
					Player1CardsImages.get(i).setImage(card);
				}
				
				for(int i=0;i<player2CardFileNames.size();i++) {
					Image card = new Image(player2CardFileNames.get(i));
					Player2CardsImages.get(i).setImage(card);
				}
				
				playPlayer1.setDisable(false);
				foldPlayer1.setDisable(false);
				
				playPlayer1.setVisible(true);
				foldPlayer1.setVisible(true);
				
				playPlayer2.setDisable(false);
				foldPlayer2.setDisable(false);
				
				playPlayer2.setVisible(true);
				foldPlayer2.setVisible(true);
				
				btnDeal.setDisable(true);
				btnDeal.setVisible(false);
				
			
				txtPlayer1Balance.setText(String.valueOf(playerOne.totalWinnings));
				txtPlayer2Balance.setText(String.valueOf(playerTwo.totalWinnings));
				anteInputPlayer1.setDisable(true);
				anteInputPlayer2.setDisable(true);
				pairplusInputPlayer1.setDisable(true);
				pairplusInputPlayer2.setDisable(true);
				
				listView.getItems().clear();
				
				listView.getItems().add("Select Play or Fold");
				
				btnDealPressed = true;
	
			}
		});
	    
	    playPlayer1.setOnAction(new EventHandler<ActionEvent>() // handles the play button
		{
			@Override
			public void handle(ActionEvent event) {
				
				playPlayer1.setDisable(true);
				foldPlayer1.setDisable(true);
				
				playPlayer1.setVisible(false);
				foldPlayer1.setVisible(false);

				ArrayList<String> dealerCardFileNames= new ArrayList<String>();
				
				playerOneFold = false;
				
				playerOne.playBet = playerOne.anteBet;
				playerOne.totalWinnings -= playerOne.playBet;
				playerOne.totalWinnings += ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
		
				txtPlayer1Balance.setText(String.valueOf(playerOne.totalWinnings));
				txtPlayer2Balance.setText(String.valueOf(playerTwo.totalWinnings));
				if (checkBothButtonClicks(playPlayer1, playPlayer2)) {
					ShowDealersCards(dealerCardFileNames, DealersCardsImages, btnDeal);
					anteInputPlayer1.setDisable(false);
					anteInputPlayer2.setDisable(false);
					pairplusInputPlayer1.setDisable(false);
					pairplusInputPlayer2.setDisable(false);
					ShowWhoWon(anteInputPlayer1, anteInputPlayer2);
				}
			}
		});
	    
	    playPlayer2.setOnAction(new EventHandler<ActionEvent>() // handles the play button
		{
			@Override
			public void handle(ActionEvent event) {
				
				playPlayer2.setDisable(true);
				foldPlayer2.setDisable(true);
				
				playPlayer2.setVisible(false);
				foldPlayer2.setVisible(false);

				ArrayList<String> dealerCardFileNames= new ArrayList<String>();

				playerTwoFold = false;
				
				playerTwo.playBet = playerTwo.anteBet;
				playerTwo.totalWinnings -= playerTwo.playBet;
				playerTwo.totalWinnings += ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);
				
				txtPlayer1Balance.setText(String.valueOf(playerOne.totalWinnings));
				txtPlayer2Balance.setText(String.valueOf(playerTwo.totalWinnings));
				if (checkBothButtonClicks(playPlayer1, playPlayer2)) {
					ShowDealersCards(dealerCardFileNames, DealersCardsImages, btnDeal);
					anteInputPlayer1.setDisable(false);
					anteInputPlayer2.setDisable(false);
					pairplusInputPlayer1.setDisable(false);
					pairplusInputPlayer2.setDisable(false);
					ShowWhoWon(anteInputPlayer1, anteInputPlayer2);

				}
			}
		});
	    
	    foldPlayer1.setOnAction(new EventHandler<ActionEvent>() // handles the fold clicks
		{
			@Override
			public void handle(ActionEvent event) {
				
				playPlayer1.setDisable(true);
				foldPlayer1.setDisable(true);
				
				playPlayer1.setVisible(false);
				foldPlayer1.setVisible(false);

				ArrayList<String> dealerCardFileNames= new ArrayList<String>();
				playerOneFold = true;
				
				if (checkBothButtonClicks(playPlayer1, playPlayer2)) {
					ShowDealersCards(dealerCardFileNames, DealersCardsImages, btnDeal);
					anteInputPlayer1.setDisable(false);
					anteInputPlayer2.setDisable(false);
					pairplusInputPlayer1.setDisable(false);
					pairplusInputPlayer2.setDisable(false);
					ShowWhoWon(anteInputPlayer1, anteInputPlayer2);
				}
			}
		});
	    
	    foldPlayer2.setOnAction(new EventHandler<ActionEvent>() // handles the fold clicks
		{
			@Override
			public void handle(ActionEvent event) {
				
				playPlayer2.setDisable(true);
				foldPlayer2.setDisable(true);
				
				playPlayer2.setVisible(false);
				foldPlayer2.setVisible(false);
				
				ArrayList<String> dealerCardFileNames= new ArrayList<String>();
				playerTwoFold = true;

				if (checkBothButtonClicks(playPlayer1, playPlayer2)) {
					ShowDealersCards(dealerCardFileNames, DealersCardsImages, btnDeal);
					anteInputPlayer1.setDisable(false);
					anteInputPlayer2.setDisable(false);
					pairplusInputPlayer1.setDisable(false);
					pairplusInputPlayer2.setDisable(false);
					ShowWhoWon(anteInputPlayer1, anteInputPlayer2);
				}
			}
		});
	    
	    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { // exits the program
            public void handle(ActionEvent e)
            {
            	System.exit(0);                            
            }
        };
        
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { // resets the entire game
            public void handle(ActionEvent e)
            {
            	playerOne = new Player();
            	playerTwo = new Player();
            	theDealer = new Dealer();
            	for(int i=0;i<DealersCardsImages.size();i++) {
        			DealersCardsImages.get(i).setImage(emptyCard);
        		}
        		
        		for(int i=0;i<Player1CardsImages.size();i++) {
        			Player1CardsImages.get(i).setImage(emptyCard);
        		}
        		
        		for(int i=0;i<Player2CardsImages.size();i++) {
        			Player2CardsImages.get(i).setImage(emptyCard);
        		}        			
            	listView.getItems().clear();
            	listView.getItems().add("Enter Your Bets");
            	
            	btnDeal.setDisable(false);
            	btnDeal.setVisible(true);
            	
            	anteInputPlayer1.setDisable(false);
				anteInputPlayer2.setDisable(false);
				pairplusInputPlayer1.setDisable(false);
				pairplusInputPlayer2.setDisable(false);
				
				playPlayer1.setDisable(true);
				foldPlayer1.setDisable(true);
				
				playPlayer1.setVisible(false);
				foldPlayer1.setVisible(false);
				
				playPlayer2.setDisable(true);
				foldPlayer2.setDisable(true);
				
				playPlayer2.setVisible(false);
				foldPlayer2.setVisible(false);
				
				txtPlayer1Balance.setText("0");
				txtPlayer2Balance.setText("0");
				
				anteInputPlayer1.setText("");
				anteInputPlayer2.setText("");
				
				pairplusInputPlayer1.setText("");
				pairplusInputPlayer2.setText("");
				
            }
        };
        
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() { // changes the theme
            public void handle(ActionEvent e)
            {
            	BackgroundFill background_fill2 = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
                Background background2= new Background(background_fill2);
         	    
         	    BP.setBackground(background2);
         	    
         	    backOfCard = new Image("gray_back.png");
         	    
         	    if(btnDealPressed) {
         	    	for(int i=0;i<DealersCardsImages.size();i++) {
         				DealersCardsImages.get(i).setImage(backOfCard);
         				DealersCardsImages.get(i).setFitHeight(200);
         				DealersCardsImages.get(i).setFitWidth(130);
         			}
         	    }
            }
        };
        
        m1.setOnAction(event);
        m2.setOnAction(event2);	
        m3.setOnAction(event3);	    
	}
	
	
	void ShowDealersCards(ArrayList<String> dealerCardFileNames, ArrayList<ImageView> DealersCardsImages, Button btnDeal ) {
		// flips over the dealers cards
		String dealercard1 = String.valueOf(theDealer.dealersHand.get(0).value) + String.valueOf(theDealer.dealersHand.get(0).suit) + ".png";
		String dealercard2 = String.valueOf(theDealer.dealersHand.get(1).value) + String.valueOf(theDealer.dealersHand.get(1).suit) + ".png";
		String dealercard3 = String.valueOf(theDealer.dealersHand.get(2).value) + String.valueOf(theDealer.dealersHand.get(2).suit) + ".png";
		
		dealerCardFileNames.add(dealercard1);
		dealerCardFileNames.add(dealercard2);
		dealerCardFileNames.add(dealercard3);
		
		for(int i=0;i<dealerCardFileNames.size();i++) {
			Image card = new Image(dealerCardFileNames.get(i));
			DealersCardsImages.get(i).setImage(card);
		}
		btnDeal.setDisable(false);
		btnDeal.setVisible(true);
	}
	
	boolean checkBothButtonClicks(Button playPlayer1, Button playPlayer2) {
		//checks if both player clicked the play or fold button
		if(playPlayer1.isDisable() && playPlayer2.isDisable()) {
			return true;
		}
		return false;
	}
	
	void ShowWhoWon(TextField anteInputPlayer1 , TextField anteInputPlayer2) {
		listView.getItems().clear();
		//  display the contents of who won
		if(!playerOneFold) {
			if(ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand) == 1) {
				System.out.println("Player One Lost to Dealer");
				listView.getItems().add("Player One Lost to Dealer");

			}
			else if(ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand) == 2){
				System.out.println("Player One Has Won");
				listView.getItems().add("Player One has Won");
				playerOne.totalWinnings += playerOne.anteBet * 2 + playerOne.playBet * 2;
				txtPlayer1Balance.setText(String.valueOf(playerOne.totalWinnings));
			}
			else {
				System.out.println("Player One's Bet is Pushed");
				listView.getItems().add("Player One's Bet is Pushed");
				playerOne.totalWinnings += playerOne.playBet;
				txtPlayer1Balance.setText(String.valueOf(playerOne.totalWinnings));
				anteInputPlayer1.setDisable(true);

			}
			
			if(playerOne.pairPlusBet > 0) {
				if(ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet) == 0) {
					listView.getItems().add("Player One Lost Pair Plus");
				}
				else {
					listView.getItems().add("Player One Won Pair Plus");
				}
			}
		}
		if(!playerTwoFold) {
			if(ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand) == 1) {
				System.out.println("Player Two Lost to Dealer");
				listView.getItems().add("Player Two Lost to Dealer");

			}
			else if(ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand) == 2){
				System.out.println("Player Two Has Won");
				listView.getItems().add("Player Two Has Won");
				playerTwo.totalWinnings += playerTwo.anteBet * 2 + playerTwo.playBet * 2;
				txtPlayer2Balance.setText(String.valueOf(playerTwo.totalWinnings));
			}
			else {
				System.out.println("Player Two's Bet is Pushed");
				listView.getItems().add("Player Two's Bet is Pushed");
				playerTwo.totalWinnings += playerTwo.playBet;
				txtPlayer2Balance.setText(String.valueOf(playerTwo.totalWinnings));
				anteInputPlayer2.setDisable(true);
			}
			if(playerTwo.pairPlusBet > 0) {
				if(ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet) == 0) {
					listView.getItems().add("Player Two Lost Pair Plus");
				}
				else {
					listView.getItems().add("Player Two Won Pair Plus");
				}
			}
		}
		listView.getItems().add("Enter Your Bets");
	}
	
	public static boolean isNumeric(String str) {  // checks if a string is a numeric value
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
