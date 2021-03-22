class Command {
    @Override
    public String toString() {
        return super.toString();
    }
}

interface ServiceInterface {
    public void processService(Command command);
}

class ServiceType {

}

class CheckoutService extends ServiceType implements ServiceInterface {

    @Override
    public void processService(Command command) {
        System.out.println("Processed Checkout");
    }
}

class ItemSubjectService extends ServiceType implements ServiceInterface {

    @Override
    public void processService(Command command) {
        System.out.println("Processed Item Subject");
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
    }
}
