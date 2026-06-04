import { useEffect, useState } from "react";
import { Input } from "@/components/ui/input";

export function QuantityInput({ quantity, onChange }) {
  const [value, setValue] = useState(quantity.toString());

  useEffect(() => {
    setValue(quantity.toString());
  }, [quantity]);

  const commit = () => {
    const qty = parseInt(value, 10);

    if (isNaN(qty) || qty < 1) {
      setValue(quantity.toString());
      return;
    }

    onChange(qty);
  };

  return (
    <Input
      type="number"
      min={1}
      value={value}
      onFocus={(e) => e.target.select()}
      onChange={(e) => setValue(e.target.value)}
      onBlur={commit}
      onKeyDown={(e) => {
        if (e.key === "Enter") {
          commit();
          e.currentTarget.blur();
        }
      }}
      className="w-20 text-center no-spinner"
    />
  );
}