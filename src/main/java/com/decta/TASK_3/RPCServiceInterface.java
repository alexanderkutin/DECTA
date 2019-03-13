
public class RPCServiceInterface {
    RpcResponse registerNewClient(Client client);
    RpcResponse registerNewBusinessClient(BusinessClient client);
    RpcResponse getPrivateClientsList(String clientId);
    RpcResponse editClients(Client client);
}
