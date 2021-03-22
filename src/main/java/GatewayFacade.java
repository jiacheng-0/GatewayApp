import java.util.ArrayList;

class Command {
    @Override
    public String toString() {
        return super.toString();
    }
}

interface ServiceInterface {
    public void processService(Command command);
}

interface ServiceFactoryInterface {
    public void invokeService(Command command);
}

class ServiceType {

}

class CheckoutService extends ServiceType implements ServiceInterface {

    @Override
    public void processService(Command command) {
        System.out.println("Processed Checkout");
    }
}

class CheckoutServiceFactory implements ServiceFactoryInterface {

    @Override
    public void invokeService(Command command) {
        CheckoutService cs = new CheckoutService();
        cs.processService(command);
    }
}

class ItemSubjectService extends ServiceType implements ServiceInterface, ItemSubjectInterface {

    ArrayList<ItemObserverInterface> serviceList = new ArrayList<>();

    @Override
    public void processService(Command command) {
        System.out.println("Processed Item Subject");
    }

    @Override
    public void register(ItemObserverInterface anObserver) {
        serviceList.add(anObserver);
    }

    @Override
    public void unregister(ItemObserverInterface anObserver) {
        serviceList.remove(anObserver);
    }

    @Override
    public void notifyRegisteredUsers() {
        for (ItemObserverInterface item: serviceList) {

        }
    }
}

class PaymentService extends ServiceType implements ServiceInterface {

    @Override
    public void processService(Command command) {
        System.out.println("Processed Payment");
    }
}

class ShippingService extends ServiceType implements ServiceInterface {

    @Override
    public void processService(Command command) {
        System.out.println("Processed Shipping");
    }
}

class GatewayFacade {

    private GatewayFacade() {

    }

    public static final GatewayFacade INSTANCE = new GatewayFacade();

    public static GatewayFacade getINSTANCE() {
        return INSTANCE;
    }

    public void invokeService(ServiceType service, Command command) {
        if (service instanceof CheckoutService) {
            ((CheckoutService) service).processService(command);
        } else if (service instanceof ItemSubjectService) {
            ((ItemSubjectService) service).processService(command);
        } else if (service instanceof PaymentService) {
            ((PaymentService) service).processService(command);
        } else if (service instanceof ShippingService) {
            ((ShippingService) service).processService(command);
        }
    }

    public static void main(String[] args) {

        Command command = new Command();
        GatewayFacade gatewayFacade = new GatewayFacade();
        ServiceType service;

        service = new CheckoutService();
        gatewayFacade.invokeService(service, command);

        service = new ItemSubjectService();
        gatewayFacade.invokeService(service, command);

        service = new PaymentService();
        gatewayFacade.invokeService(service, command);

        service = new ShippingService();
        gatewayFacade.invokeService(service, command);

        System.out.println("Task 3:");
        CheckoutServiceFactory csf = new CheckoutServiceFactory();
        csf.invokeService(command);
    }
}

// Task 4
interface ItemSubjectInterface {

    public void register(ItemObserverInterface anObserver);

    public void unregister(ItemObserverInterface anObserver);

    public void notifyRegisteredUsers();
}

interface ItemObserverInterface {

}