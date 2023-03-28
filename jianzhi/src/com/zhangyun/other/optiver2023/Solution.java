package com.zhangyun.other.optiver2023;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * 疑问：不太对啊，sample output里，U后面跟了三个数，但是看程序应该是根四个数才对，，不懂了。
 *
 * 难点：
 * 1，保证返回的update的有序性，就应该用有序的hashmap，但是这里用的是常规的hashmap，这就不知道怎么解决了
 * */

public class Solution {
    public class TruckPosition {
        public double mX;
        public double mY;
    }

    public class TruckPositionDelta {
        public int mTruckId;
        public double mDeltaX;
        public double mDeltaY;
    }

    public interface IServer {
        public TruckPosition SubscribeToTruck(int truckId);
    }

    public interface ISubscriber {
        // Called by server after initial subscription
        public void ProcessUpdate(final TruckPositionDelta positionDelta);

        // Called by clients
        public TruckPosition SubscribeToTruck(int truckId, int clientId);
        public List<TruckPositionDelta> GetUpdates(int clientId);
    }

    class Subscriber implements ISubscriber {
        private final IServer mServer;

        public Subscriber(IServer server) {
            mServer = server;
        }

        @Override
        public void ProcessUpdate(final TruckPositionDelta positionDelta) {
        }

        @Override
        public TruckPosition SubscribeToTruck(int truckId, int clientId) {
            return new TruckPosition();
        }

        @Override
        public List<TruckPositionDelta> GetUpdates(int clientId) {
            return new ArrayList<TruckPositionDelta>();
        }
    }

    class Server implements IServer {
        private HashSet<Integer> mRegisteredTrucks;
        private HashMap<Integer, TruckPosition> mCurrentPos;

        public Server() {
            mRegisteredTrucks = new HashSet<>();
            mCurrentPos = new HashMap<>();
        }

        @Override
        public TruckPosition SubscribeToTruck(int truckId) {
            mRegisteredTrucks.add(truckId);
            TruckPosition pos = mCurrentPos.get(truckId);
            TruckPosition copy = new TruckPosition();
            copy.mX = pos.mX;
            copy.mY = pos.mY;
            return copy;
        }

        public void AddPosition(int truckId, TruckPosition pos) {
            mCurrentPos.put(truckId, pos);
        }

        public void OnUpdate(Subscriber subscriber, final TruckPositionDelta delta) {
            if (mRegisteredTrucks.contains(delta.mTruckId))
            {
                subscriber.ProcessUpdate(delta);
            }
            TruckPosition pos = mCurrentPos.get(delta.mTruckId);
            pos.mX += delta.mDeltaX;
            pos.mY += delta.mDeltaY;
        }
    }

    class Client {
        private final int mClientId;
        private final Subscriber mSubscriber;
        private final DecimalFormat mFormat;

        public Client(int clientId, Subscriber subscriber) {
            mClientId = clientId;
            mSubscriber = subscriber;
            mFormat = new DecimalFormat("0.#");
        }

        public void Subscribe(int truckId) {
            TruckPosition pos = mSubscriber.SubscribeToTruck(truckId, mClientId);
            System.out.println("S " + mClientId + " " + truckId + " " + mFormat.format(pos.mX) + " " + mFormat.format(pos.mY));
        }

        public void RequestUpdate() {
            List<TruckPositionDelta> updates = mSubscriber.GetUpdates(mClientId);
            for (final TruckPositionDelta delta : updates) {
                System.out.println("U " + mClientId + " " + delta.mTruckId + " " + mFormat.format(delta.mDeltaX) + " " + mFormat.format(delta.mDeltaY));
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Server server = solution.new Server();
        Subscriber subscriber = solution.new Subscriber(server);
        List<Client> clients = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int numTrucks = scanner.nextInt();
        for (int i = 0; i < numTrucks; i++) {
            TruckPosition pos = solution.new TruckPosition();
            pos.mX = scanner.nextDouble();
            pos.mY = scanner.nextDouble();
            server.AddPosition(i, pos);
        }

        while (scanner.hasNext()) {
            char command = scanner.next().charAt(0);
            if (command == 'S') {
                int clientId = scanner.nextInt();
                if (clientId >= clients.size()) {
                    clients.add(solution.new Client(clientId, subscriber));
                }
                clients.get(clientId).Subscribe(scanner.nextInt());
            } else if (command == 'U') {
                TruckPositionDelta delta = solution.new TruckPositionDelta();
                delta.mTruckId = scanner.nextInt();
                delta.mDeltaX = scanner.nextDouble();
                delta.mDeltaY = scanner.nextDouble();
                server.OnUpdate(subscriber, delta);
            } else if (command == 'R') {
                int clientId = scanner.nextInt();
                clients.get(clientId).RequestUpdate();
            } else {
                throw new IllegalArgumentException("Invalid input");
            }
        }
    }
}
