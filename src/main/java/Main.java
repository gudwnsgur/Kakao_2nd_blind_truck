import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String x_auth_token = "7bf2bd0df3c7615afd6f9052f9f56c11";
        int problem = 1;
        String authKey = Connection.getInstance().start(x_auth_token, problem);

        JSONParser jsonParser = new JSONParser();
        ArrayList<Location> locations;
        ArrayList<Truck> trucks;

        for (int i = 0; i < 720; i++) {
            locations = jsonParser.getLocationsFromLocation(
                    Connection.getInstance().locations(authKey)
            );
            trucks = jsonParser.getTrucksFromTruck(
                    Connection.getInstance().trucks(authKey)
            );

            ArrayList<Command> commands = new ArrayList<Command>();
            ArrayList<Integer> cmd = new ArrayList<Integer>();
            cmd.add(0);
            Command command = new Command(0, cmd);
            commands.add(command);
            System.out.println(Connection.getInstance().simulate(authKey, commands));
        }
        System.out.println("SCORE : " + Connection.getInstance().score(authKey));


    }
}
