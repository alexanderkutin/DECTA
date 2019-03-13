
public class RPCService implements RPCServiceInterface {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private RestTemplate restTemplate;

    public RPCService() {
        restTemplate = new RestTemplate();
    }

    private RpcResponse callResponse(String url, String method, RpcRequest request) {
		RpcResponse response = null;
		
		try {
            response = restTemplate.url(url).method(method).body(request).execute();
        } catch (Exception e) {
            log.info("RPC call failed");
        }
		
		return response;
	}

    @Override
    public RpcResponse registerNewClient(Client client) {
        log.debug("Register new client RPC service called with parameters - " + client);

        RpcRequest request = new RpcRequest()
                .withJsonRpc("2.0")
                .withMethod("REGISTER_NEW_CLIENT")
                .withId("1")
                .withParameters(client);

        log.debug("Register new client RPC request prepared with parameters - " + request);

        RpcResponse response = callResponse("https://server.host.lv/client", "POST", request);

        log.debug("Register new client RPC response received - " + response);

        return response;
    }

    @Override
    public RpcResponse registerNewBusinessClient(BusinessClient client) {
        log.debug("Register new client RPC service called with parameters - " + client);

        RpcRequest request = new RpcRequest()
                .withJsonRpc("2.0")
                .withMethod("REGISTER_NEW__BUSINESS_CLIENT")
                .withId("1")
                .withParameters(client);

        log.debug("Register new client RPC request prepared with parameters - " + request);

		RpcResponse response = callResponse("https://server.host.lv/client", "POST", request);

        log.debug("Register new client RPC response received - " + response);

        if(response.getErrorCode() != 0) throw new RPCServiceException("Unknown exception");

        return response;
    }

    @Override
    public RpcResponse getPrivateClientsList(String clientId) {
        log.debug("Register new client RPC service called with parameters - " + clientId);

        RpcRequest request = new RpcRequest()
                .withJsonRpc("2.0")
                .withMethod("GET_REGISTERED_CLIENTS")
                .withId("1")
                .withParameters(clientId);

        log.debug("Register new client RPC request prepared with parameters - " + request);

		RpcResponse response = callResponse("https://server.host.lv/client", "GET", request);

        log.debug("Register new client RPC response received - " + response);

        if(response.getErrorCode() != 0) throw new RPCServiceException("Unknown exception");

        return response;
    }

    @Override
    public RpcResponse editClients(Client client) {
        log.debug("Register new client RPC service called with parameters - " + client);

        RpcRequest request = new RpcRequest()
                .withJsonRpc("2.0")
                .withMethod("EDIT_CLIENT")
                .withId("1")
                .withParameters(client);

        log.debug("Register new client RPC request prepared with parameters - " + request);

		RpcResponse response = callResponse("https://server.host.lv/client", "PATCH", request);

        log.debug("Register new client RPC response received - " + response);

        if(response.getErrorCode() != 0) throw new RPCServiceException("Unknown exception");

        return response;
    }
}
