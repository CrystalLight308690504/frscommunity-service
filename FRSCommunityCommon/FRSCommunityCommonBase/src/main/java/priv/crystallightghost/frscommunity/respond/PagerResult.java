package priv.crystallightghost.frscommunity.respond;

import lombok.Data;

/**
 * @Date 2022/2/18
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class PagerResult {
    private Object data;
    private Boolean hasNext;

    public PagerResult(Object data, Boolean hasNext) {
        this.data = data;
        this.hasNext = hasNext;
    }
}
